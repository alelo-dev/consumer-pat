package br.com.alelo.consumer.consumerpat.config;


import br.com.alelo.consumer.consumerpat.model.exception.CustomException;
import br.com.alelo.consumer.consumerpat.model.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(CustomException ex,
                                                                           WebRequest request) {
        return handleException(ex, request, ex.getCode(), ex.getInternalErrorCode());
    }

    private ResponseEntity<ExceptionResponse> handleException(RuntimeException ex, WebRequest request,
                                                              HttpStatus status, String internalErrorCode) {
        final String path = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping" +
                ".pathWithinHandlerMapping", 0);
        final ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), status.value(),
                ex.getLocalizedMessage(), path, internalErrorCode);
        return new ResponseEntity<>(exceptionResponse, status);
    }

}
