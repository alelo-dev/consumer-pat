package br.com.alelo.consumer.consumerpat.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.alelo.consumer.consumerpat.controller.dto.ErrorMessageDto;
import br.com.alelo.consumer.consumerpat.exception.InsuficientBalanceException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageDto exceptionHandler(final Exception e, final WebRequest request) {
        return ErrorMessageDto.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).timestamp(Instant.now())
                .message(e.getMessage()).client(request.getDescription(true)).build();
    }
    
    @ExceptionHandler(InsuficientBalanceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageDto InsuficientBalanceExceptionHandler(final Exception e, final WebRequest request) {
        return ErrorMessageDto.builder().status(HttpStatus.BAD_REQUEST.value()).timestamp(Instant.now())
                .message(e.getMessage()).client(request.getDescription(true)).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto NotFoundException(final NotFoundException e, final WebRequest request) {
        return ErrorMessageDto.builder().status(HttpStatus.NOT_FOUND.value()).timestamp(Instant.now())
                .message(e.getMessage()).client(request.getDescription(true)).build();
    }
    
}