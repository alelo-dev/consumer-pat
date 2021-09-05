package br.com.alelo.consumer.pat.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({AlreadyExistsCardsException.class, InvalidCardTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private DefaultErrorResponse handleBadRequestException(BusinessException ex) {
        return DefaultErrorResponse.builder()
            .message(ex.getMessage())
            .build();
    }

    @ExceptionHandler({CardNotFoundException.class, ConsumerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private DefaultErrorResponse handleNotFoundException(BusinessException ex) {
        return DefaultErrorResponse.builder()
            .message(ex.getMessage())
            .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    private static class DefaultErrorResponse {
        private String message;
    }
}
