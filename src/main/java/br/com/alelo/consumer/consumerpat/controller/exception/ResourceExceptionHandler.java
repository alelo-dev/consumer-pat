package br.com.alelo.consumer.consumerpat.controller.exception;


import br.com.alelo.consumer.consumerpat.service.exception.InvalidTransactionException;
import br.com.alelo.consumer.consumerpat.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<StandardError> invalidOperation(InvalidTransactionException e) {
        StandardError err = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .msg(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e) {
        StandardError err = StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .msg(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> notWaitingException(Exception e) {
        StandardError err = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .msg(e.getLocalizedMessage())
                .obs("Erro não esperado")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
