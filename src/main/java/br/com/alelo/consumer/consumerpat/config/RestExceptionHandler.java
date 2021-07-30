package br.com.alelo.consumer.consumerpat.config;

import br.com.alelo.consumer.consumerpat.dto.BaseHttpResponse;
import br.com.alelo.consumer.consumerpat.dto.ErrorHttpResponse;
import br.com.alelo.consumer.consumerpat.exception.InternalException;
import br.com.alelo.consumer.consumerpat.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import br.com.alelo.consumer.consumerpat.util.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }
        return new ResponseEntity<>(BaseHttpResponse.of(ErrorHttpResponse.builder()
                .developerMessage(ex.getMessage())
                .userMessage(ObjectUtils.getDisplayString(body))
                .build()), headers, status);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleValidationException(NotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getDetails(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<Object> handleInternalException(InternalException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getDetails(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultException(Exception ex, WebRequest request) {
        return handleInternalException(new InternalException(getMessage(ex), ex, Constants.AN_UNEXPECTED_ERROR_HAS_OCCURRED), request);
    }

    private String getMessage(Exception ex) {
        Throwable cause = ex;
        int level = 0;
        while (cause.getCause() != cause && Objects.nonNull(cause.getCause()) && ++level < 10) {
            cause = cause.getCause();
        }
        final String message = cause.getMessage();
        return ObjectUtils.isEmpty(message) ? cause.toString() : message;
    }
}
