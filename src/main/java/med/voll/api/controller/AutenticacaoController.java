package med.voll.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosAutenticacao;


@RestController
@RequestMapping("login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    public AutenticacaoController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody @Valid DadosAutenticacao dadosAutenticacao) {

        final var token = new UsernamePasswordAuthenticationToken(
                dadosAutenticacao.login(),
                dadosAutenticacao.senha());

        final var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
