package med.voll.api.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.EnderecoDto;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class EnderecoEntity {

    private String logradouro;

    private String bairro;

    private String cep;

    private String numero;

    private String complemento;

    private String cidade;

    private String uf;

    public void atualizarInformacoes(EnderecoDto dados) {

        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }

        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }

        if (dados.cep() != null) {
            this.cep = dados.cep();
        }

        if (dados.uf() != null) {
            this.uf = dados.uf();
        }

        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }

        if (dados.numero() != null) {
            this.numero = dados.numero();
        }

        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
    }
}
