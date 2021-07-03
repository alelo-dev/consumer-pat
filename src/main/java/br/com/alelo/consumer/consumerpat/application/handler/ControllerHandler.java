package br.com.alelo.consumer.consumerpat.application.handler;

import br.com.alelo.consumer.consumerpat.domain.service.exception.ApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    protected ResponseEntity<Object> handle(ApiException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Error(ex.getCode().getMessage()),
                new HttpHeaders(), ex.getStatus(), request);
    }
}
