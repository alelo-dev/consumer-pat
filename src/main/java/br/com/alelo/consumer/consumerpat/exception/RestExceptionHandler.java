package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.exception.error.MessageError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<MessageError> notFoundExceptionHandler(NotFoundException e){
        return new ResponseEntity<>(
                new MessageError("Record not found for ID: " + e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
