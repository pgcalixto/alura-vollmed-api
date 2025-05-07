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
import med.voll.api.dto.AtualizacaoMedicoDto;
import med.voll.api.dto.CadastroMedicoDto;
import med.voll.api.dto.ListagemMedicoDto;
import med.voll.api.dto.MedicoDto;
import med.voll.api.mapper.ListagemMedicoDtoMapper;
import med.voll.api.mapper.MedicoEntityMapper;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private final ListagemMedicoDtoMapper listagemMedicoDtoMapper;

    private final MedicoEntityMapper medicoEntityMapper;

    private final MedicoRepository repository;

    public MedicoController(
            ListagemMedicoDtoMapper listagemMedicoDtoMapper,
            MedicoEntityMapper medicoEntityMapper,
            MedicoRepository repository) {
        this.listagemMedicoDtoMapper = listagemMedicoDtoMapper;
        this.medicoEntityMapper = medicoEntityMapper;
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDto> cadastrar(
            @RequestBody @Valid CadastroMedicoDto dados,
            UriComponentsBuilder uriComponentsBuilder) {

        final var medicoEntity = medicoEntityMapper.toMedicoEntity(dados);

        repository.save(medicoEntity);

        final var medicoDto = medicoEntityMapper.toMedicoDto(medicoEntity);

        final var uri = uriComponentsBuilder
                .path("/medicos/{id}")
                .buildAndExpand(medicoDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(medicoDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDto>> listar(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {

        final Page<ListagemMedicoDto> pagina = repository
                .findAllByAtivoTrue(paginacao)
                .map(listagemMedicoDtoMapper::toListagemMedicoDto);

        return ResponseEntity.ok(pagina);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDto> atualizar(@RequestBody @Valid AtualizacaoMedicoDto dados) {

        final var medicoEntity = repository.getReferenceById(dados.id());

        medicoEntity.atualizarInformacoes(dados);

        final var medicoDto = medicoEntityMapper.toMedicoDto(medicoEntity);

        return ResponseEntity.ok(medicoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDto> detalhar(@PathVariable Long id) {

        final var medico = repository.getReferenceById(id);

        final var medicoDto = medicoEntityMapper.toMedicoDto(medico);

        return ResponseEntity.ok(medicoDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        final var medico = repository.getReferenceById(id);

        medico.excluir();

        return ResponseEntity.noContent().build();
    }

}
