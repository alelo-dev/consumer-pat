package br.com.alelo.consumer.consumerpat.exception.handler;

import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.exception.error.MessageError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@Slf4j
@RestControllerAdvice
public class GeneralHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<MessageError> notFoundExceptionHandler(NotFoundException e){

        log.error("Record not found for ID: " + e.getMessage());

        return new ResponseEntity<>(
                new MessageError("Record not found for ID: " + e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<MessageError> badRequestException(BadRequestException e){

        log.error(e.getMessage());

        return new ResponseEntity<>(
                new MessageError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<MessageError> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e){

        log.error("There was an integrity error: " + e.getMessage());

        return new ResponseEntity<>(
                new MessageError("There was an integrity error. Please validate the data"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<MessageError> methodArgumentNotValidException(MethodArgumentNotValidException e){

        log.error(e.getMessage());

        return new ResponseEntity<>(
                new MessageError(e.getBindingResult().getAllErrors()), HttpStatus.BAD_REQUEST);
    }
}
