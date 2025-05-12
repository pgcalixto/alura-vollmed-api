package med.voll.api.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.Usuario;

@Service
public class TokenService {

    private final String jwtSecret;

    public TokenService(@Value("${api.security.token.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String gerarTokenJwt(Usuario usuario) {

        final Algorithm algoritmo = Algorithm.HMAC256(jwtSecret);

        try {
            return JWT
                .create()
                .withIssuer("API Voll.med")
                .withSubject(usuario.getUsername())
                .withExpiresAt(gerarDataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao gerar token JWT", ex);
        }
    }

    private Instant gerarDataExpiracao() {

        final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

        final ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(zoneId);

        return zonedDateTimeNow.plusHours(2).toInstant();
    }

    public String obterSubject(String tokenJwt) {

        final Algorithm algoritmo = Algorithm.HMAC256(jwtSecret);

        try {
            return JWT
                .require(algoritmo)
                .withIssuer("API Voll.med")
                .build()
                .verify(tokenJwt)
                .getSubject();
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

}
