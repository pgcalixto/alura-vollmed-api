package med.voll.api.dto;

import med.voll.api.domain.Especialidade;

public record ListagemMedicoDto(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

}
