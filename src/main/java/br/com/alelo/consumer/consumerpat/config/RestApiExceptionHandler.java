package br.com.alelo.consumer.consumerpat.config;

import br.com.alelo.consumer.consumerpat.common.domain.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;
    private static final String INVALID_INPUT_DATA_MESSAGE = "Invalid input data.";
    private static final String UNEXPECTED_ERROR_MESSAGE = "There was an unexpected error, we are working to solve it.";
    private static final String NESTED_OBJECT_MATCH_PATTERN = "(.*)\\[\\d+\\](.*)";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return handleValidationException(ex, headers, status, request, ex.getBindingResult());
    }
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {

        return handleValidationException(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        return Optional.of(buildApiError(status, UNEXPECTED_ERROR_MESSAGE, request, null))
                .map(apiError -> handleExceptionInternal(ex, apiError, headers, status, request))
                .orElseThrow();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        var httpStatus = HttpStatus.BAD_REQUEST;
        var errorDetails = collectErrorDetails(ex);

        return Optional.of(buildApiError(httpStatus, INVALID_INPUT_DATA_MESSAGE, request, errorDetails))
                .map(apiError -> handleExceptionInternal(ex, apiError, new HttpHeaders(), httpStatus, request))
                .orElseThrow();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        var httpStatus = HttpStatus.NOT_FOUND;

        return Optional.of(buildApiError(httpStatus, ex.getMessage(), request, null))
                .map(apiError -> handleExceptionInternal(ex, apiError, new HttpHeaders(), httpStatus, request))
                .orElseThrow();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtException(Exception ex, WebRequest request) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        log.error(ex.getMessage(), ex);

        return Optional.of(buildApiError(httpStatus, UNEXPECTED_ERROR_MESSAGE, request, null))
                .map(apiError -> handleExceptionInternal(ex, apiError, new HttpHeaders(), httpStatus, request))
                .orElseThrow();
    }

    private ResponseEntity<Object> handleValidationException(Exception ex, HttpHeaders headers, HttpStatus status,
                                                             WebRequest request,
                                                             BindingResult bindingResult) {

        return Optional.ofNullable(bindingResult)
                .map(this::collectObjectErrorDetails)
                .map(errorDetails -> buildApiError(status, INVALID_INPUT_DATA_MESSAGE, request, errorDetails))
                .map(apiError -> handleExceptionInternal(ex, apiError, headers, status, request))
                .orElseThrow();
    }

    private RestApiError buildApiError(HttpStatus status, String detail, WebRequest request,
                                       List<RestApiError.RestApiErrorDetail> details) {

        return RestApiError.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .uri(((ServletWebRequest)request).getRequest().getRequestURI())
                .userMessage(detail)
                .details(details)
                .build();
    }

    private List<RestApiError.RestApiErrorDetail> collectErrorDetails(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(constraintViolation -> {
                    var name = constraintViolation.getPropertyPath().toString();
                    var userMessage = constraintViolation.getMessage();

                    return RestApiError.RestApiErrorDetail.builder()
                            .name(name)
                            .userMessage(userMessage)
                            .build();
                }).collect(Collectors.toList());
    }

    private List<RestApiError.RestApiErrorDetail> collectObjectErrorDetails(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    var userMessage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    var name = (objectError instanceof FieldError) ?
                            ((FieldError) objectError).getField() : objectError.getObjectName();

                    if (userMessage.matches(NESTED_OBJECT_MATCH_PATTERN))
                        userMessage = objectError.getDefaultMessage();

                    return RestApiError.RestApiErrorDetail.builder()
                            .name(name)
                            .userMessage(userMessage)
                            .build();

                }).collect(Collectors.toList());
    }
}