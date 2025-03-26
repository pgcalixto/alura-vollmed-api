package med.voll.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.AtualizacaoMedicoDto;
import med.voll.api.dto.CadastroMedicoDto;
import med.voll.api.dto.ListagemMedicoDto;
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
    public void cadastrar(@RequestBody @Valid CadastroMedicoDto dados) {

        final var medicoEntity = medicoEntityMapper.toMedicoEntity(dados);

        repository.save(medicoEntity);
    }

    @GetMapping
    public Page<ListagemMedicoDto> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(listagemMedicoDtoMapper::toListagemMedicoDto);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoMedicoDto dados) {

        final var medico = repository.getReferenceById(dados.id());

        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {

        final var medico = repository.getReferenceById(id);

        medico.excluir();
    }

}
