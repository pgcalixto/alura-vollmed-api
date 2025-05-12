package med.voll.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApiApplicationAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RespostaMethodArgumentNotValidException>> tratarMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        final var erros = e.getFieldErrors();

        final var respostas = erros.stream()
                .map(RespostaMethodArgumentNotValidException::new)
                .toList();

        return ResponseEntity.badRequest().body(respostas);
    }

    private record RespostaMethodArgumentNotValidException(String campo, String mensagem) {

        public RespostaMethodArgumentNotValidException(FieldError e) {
            this(e.getField(), e.getDefaultMessage());
        }
    }

}
