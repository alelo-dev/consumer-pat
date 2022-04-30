package br.com.alelo.consumer.consumerpat.controller;


import br.com.alelo.consumer.consumerpat.dto.Errors;
import br.com.alelo.consumer.consumerpat.exception.ErrorBaseException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationAdvice {

    @ExceptionHandler(ErrorBaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleBadExceptionException(ErrorBaseException ex){
        return new Errors(ex.getMessage());
    }

    @ExceptionHandler(NotFoundBaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Errors handleNotFoundExceptionException(NotFoundBaseException ex){
        return new Errors(ex.getMessage());
    }
}
