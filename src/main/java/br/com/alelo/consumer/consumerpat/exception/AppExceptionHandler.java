package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error!";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public final ApiErrors handlerInternalServerError(Exception ex, WebRequest request) {
        return buildResult(List.of(), INTERNAL_SERVER_ERROR_MESSAGE);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public final ApiErrors handlerResourceNotFound(Exception ex, WebRequest request) {
        return buildResult(List.of(), ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public final ApiErrors handlerBusinessException(Exception ex, WebRequest request) {
        return buildResult(List.of(), ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrors handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(e -> errors.put(e.getPropertyPath().toString(), e.getMessage()));
        return buildResult(errors, request.getDescription(false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ApiErrors handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(e -> errors.put(((FieldError) e).getField(), e.getDefaultMessage()));
        return buildResult(errors, request.getDescription(false));
    }

    private ApiErrors buildResult(Object errors, String details) {
        return ApiErrors.builder()
            .timestamp(LocalDateTime.now())
            .errors(errors)
            .details(details)
            .build();
    }
}
