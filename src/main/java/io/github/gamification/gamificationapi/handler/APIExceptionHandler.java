package io.github.gamification.gamificationapi.handler;

import io.github.gamification.gamificationapi.exception.IncorrectPasswordException;
import io.github.gamification.gamificationapi.exception.PersonagemNotFoundException;
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
    public ResponseEntity handleUsuarioExistsException(UsuarioExistsException e){
        return defaultExceptionMessage(e.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity handleUsuarioNotFoundException(){
        return defaultExceptionMessage("Usuário não encontrado");
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity handleIncorrectPasswordException(){
        return defaultExceptionMessage("Senha incorreta");
    }


    @ExceptionHandler(PersonagemNotFoundException.class)
    public ResponseEntity handlePersonagemNotFoundException(){
        return defaultExceptionMessage("Personagem inexistente");
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
