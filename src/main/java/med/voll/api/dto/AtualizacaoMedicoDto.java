package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoMedicoDto(

        @NotNull
        Long id,

        String nome,

        String telefone,

        EnderecoDto endereco) {

}
