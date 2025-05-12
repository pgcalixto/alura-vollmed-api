package med.voll.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import med.voll.api.dto.AtualizacaoPacienteDto;
import med.voll.api.dto.CadastroPacienteDto;
import med.voll.api.dto.ListagemPacienteDto;
import med.voll.api.dto.PacienteDto;
import med.voll.api.mapper.ListagemPacienteDtoMapper;
import med.voll.api.mapper.PacienteEntityMapper;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    private final ListagemPacienteDtoMapper listagemPacienteDtoMapper;

    private final PacienteEntityMapper pacienteEntityMapper;

    private final PacienteRepository pacienteRepository;

    public PacienteController(
            ListagemPacienteDtoMapper listagemPacienteDtoMapper,
            PacienteEntityMapper pacienteEntityMapper,
            PacienteRepository repository) {
        this.listagemPacienteDtoMapper = listagemPacienteDtoMapper;
        this.pacienteEntityMapper = pacienteEntityMapper;
        this.pacienteRepository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteDto> cadastrar(
            @RequestBody @Valid CadastroPacienteDto dados,
            UriComponentsBuilder uriComponentsBuilder) {

        final var pacienteEntity = pacienteEntityMapper.toPacienteEntity(dados);

        pacienteRepository.save(pacienteEntity);

        final var pacienteDto = pacienteEntityMapper.toPacienteDto(pacienteEntity);

        final var uri = uriComponentsBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(pacienteDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(pacienteDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDto>> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        final Page<ListagemPacienteDto> pagina = pacienteRepository
                .findAllByAtivoTrue(paginacao)
                .map(listagemPacienteDtoMapper::toListagemPacienteDto);

        return ResponseEntity.ok(pagina);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteDto> atualizar(@RequestBody @Valid AtualizacaoPacienteDto dados) {

        final var pacienteEntity = pacienteRepository.getReferenceById(dados.id());

        pacienteEntity.atualizarInformacoes(dados);

        final var pacienteDto = pacienteEntityMapper.toPacienteDto(pacienteEntity);

        return ResponseEntity.ok(pacienteDto);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteDto> detalhar(@PathVariable Long id) {

        final var paciente = pacienteRepository.getReferenceById(id);

        final var pacienteDto = pacienteEntityMapper.toPacienteDto(paciente);

        return ResponseEntity.ok(pacienteDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        final var paciente = pacienteRepository.getReferenceById(id);

        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

}
