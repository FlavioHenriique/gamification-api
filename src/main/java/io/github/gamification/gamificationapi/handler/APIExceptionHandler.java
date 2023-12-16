package io.github.gamification.gamificationapi.handler;

import io.github.gamification.gamificationapi.exception.UsuarioExistsException;
import io.github.gamification.gamificationapi.exception.UsuarioNotFoundException;
import io.github.gamification.gamificationapi.model.ExceptionBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(UsuarioExistsException.class)
    public ResponseEntity handleUsuarioExistsException(){
        return defaultExceptionMessage("Email já utilizado");
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity handleUsuarioNotFoundException(){
        return defaultExceptionMessage("Usuário não encontrado");
    }

    private ResponseEntity defaultExceptionMessage(String message){
        return ResponseEntity
                .badRequest()
                .body(
                        ExceptionBody
                                .builder()
                                .code(BAD_REQUEST.value())
                                .message(message)
                                .build());
    }
}
