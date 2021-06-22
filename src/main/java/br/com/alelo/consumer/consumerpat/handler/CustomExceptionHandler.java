package br.com.alelo.consumer.consumerpat.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    protected ExceptionResult handleEntityNotFound(EntityNotFoundException exception) {
        return new ExceptionResult(exception.getMessage());
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    protected ExceptionResult handleIllegalArgument(IllegalArgumentException exception) {
        return new ExceptionResult(exception.getMessage());
    }
}
