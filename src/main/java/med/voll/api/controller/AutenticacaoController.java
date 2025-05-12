package med.voll.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.Usuario;
import med.voll.api.dto.DadosAutenticacao;
import med.voll.api.dto.TokenJwtDto;
import med.voll.api.service.TokenService;


@RestController
@RequestMapping("login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AutenticacaoController(
            AuthenticationManager authenticationManager,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJwtDto> login(@RequestBody @Valid DadosAutenticacao dadosAutenticacao) {

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                dadosAutenticacao.login(),
                dadosAutenticacao.senha());

        final Authentication authentication = authenticationManager.authenticate(token);

        final String tokenJwt = tokenService.gerarTokenJwt((Usuario) authentication.getPrincipal());

        final TokenJwtDto tokenJwtDto = new TokenJwtDto(tokenJwt);

        return ResponseEntity.ok(tokenJwtDto);
    }

}
