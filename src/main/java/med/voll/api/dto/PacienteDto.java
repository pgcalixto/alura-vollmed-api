package med.voll.api.dto;

public record PacienteDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDto endereco,
        Boolean ativo) {

}
