package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.exception.error.MessageError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<MessageError> notFoundExceptionHandler(NotFoundException e){
        return new ResponseEntity<>(
                new MessageError("Record not found for ID: " + e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<MessageError> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e){
        return new ResponseEntity<>(
                new MessageError("There was an integrity error. Please validate the data"), HttpStatus.BAD_REQUEST); // TODO Pegar o retorno, se a API for externa vai vazar dados?
    }
}
