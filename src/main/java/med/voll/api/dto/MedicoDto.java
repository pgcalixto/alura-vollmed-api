package med.voll.api.dto;

import med.voll.api.domain.Especialidade;

public record MedicoDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        EnderecoDto endereco,
        Boolean ativo) {

}
