package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.exception.BadGatewayException;
import br.com.alelo.consumer.consumerpat.exception.MethodArgumentTypeMismatchException;
import br.com.alelo.consumer.consumerpat.exception.UnauthorizedException;
import br.com.alelo.consumer.consumerpat.exception.UnprocessableEntityException;
import br.com.alelo.consumer.consumerpat.presenter.ErrorDetail;
import br.com.alelo.consumer.consumerpat.presenter.ErrorPresenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * @author Guilherme de Castro
 * @created 31/08/2021 - 10:52
 */
@ControllerAdvice
public class HandlerController {

    public static final Logger logger = LoggerFactory.getLogger(HandlerController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorPresenter> handlerIllegalArgumentExceptionHandler(final IllegalArgumentException e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST)
                .body(getError(e, BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorPresenter> handlerException(final Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(getError(e, INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorPresenter> handlerMethodArgumentTypeMismatchException(final Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(getError(e, INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorPresenter> handlerUnprocessableEntityException(
            final UnprocessableEntityException e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
                .body(getError(e, UNPROCESSABLE_ENTITY));
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<ErrorPresenter> handlerBadGatewayException(
            final BadGatewayException e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(BAD_GATEWAY)
                .body(getError(e, BAD_GATEWAY));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorPresenter> handlerUnauthorizedException(final UnauthorizedException e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
                .body(getError(e, UNPROCESSABLE_ENTITY));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorPresenter> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage());
        final var result = ex.getBindingResult();
        final var errors = result.getFieldErrors().stream()
                .map(it -> {
                    final var error = new ErrorDetail();
                    error.setInformationCode("error.request." + it.getField() + ".invalid." + it.getCode());
                    error.setMessage(it.getDefaultMessage());
                    return error;
                })
                .collect(Collectors.toSet());

        final var errorPresenter = getError(ex, UNPROCESSABLE_ENTITY);
        errorPresenter.setErrors(errors);
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
                .body(errorPresenter);
    }

    private ErrorPresenter getError(final Throwable t, final HttpStatus status) {
        final var errors = getErrorDetails(t);
        return ErrorPresenter.builder()
                .errors(errors)
                .status(status)
                .build();
    }

    private Set<ErrorDetail> getErrorDetails(final Throwable e) {
        final var details = new ErrorDetail();
        details.setInformationCode("error.business.request");
        details.setMessage(e.getMessage());
        return Set.of(details);
    }
}
