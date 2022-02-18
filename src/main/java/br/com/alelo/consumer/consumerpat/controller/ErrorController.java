package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.ErrorMessage;
import br.com.alelo.consumer.consumerpat.exception.CannotCreateExistingConsumerException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.InvalidEstablishmentTypeException;
import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage cannotCreateExistingConsumerException(CannotCreateExistingConsumerException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage consumerNotFoundException(ConsumerNotFoundException ex) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidEstablishmentTypeException(InvalidEstablishmentTypeException ex) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public ErrorMessage notEnoughBalanceException(NotEnoughBalanceException ex) {
        return new ErrorMessage(HttpStatus.PAYMENT_REQUIRED.value(), ex.getMessage());
    }

}
