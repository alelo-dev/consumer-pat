package br.com.alelo.consumer.consumerpat.handler;

import br.com.alelo.consumer.consumerpat.dto.ErrorMessageDto;
import br.com.alelo.consumer.consumerpat.exception.ConsumerDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(ConsumerDataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageDto dataNotFoundException(ConsumerDataNotFoundException excp, WebRequest request) {
        return ErrorMessageDto.builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(excp.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageDto dataNotFoundException(Exception excp, WebRequest request) {
        return ErrorMessageDto.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(excp.getMessage())
                .build();
    }
}
