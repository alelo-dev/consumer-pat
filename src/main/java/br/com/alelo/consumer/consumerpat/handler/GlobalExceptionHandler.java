package br.com.alelo.consumer.consumerpat.handler;

import br.com.alelo.consumer.consumerpat.dto.response.BaseResponse;
import br.com.alelo.consumer.consumerpat.exceptions.AppErrors;
import br.com.alelo.consumer.consumerpat.exceptions.BaseException;
import br.com.alelo.consumer.consumerpat.exceptions.CardExistsException;
import br.com.alelo.consumer.consumerpat.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exceptions.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exceptions.InvalidBalanceException;
import br.com.alelo.consumer.consumerpat.exceptions.message.BaseMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final BaseMessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        if (BaseException.class.isAssignableFrom(e.getClass())) {
            return handleException(((BaseException) e).error(), e.getMessage(), e);
        }

        return handleException(AppErrors.SERVER_ERROR, e.getMessage(), e);
    }

    @ExceptionHandler(CardExistsException.class)
    public ResponseEntity<Object> handleCardExistsException(CardExistsException e, WebRequest request) {
        return handleException(AppErrors.CARD_ALREADY_EXISTS, e.getMessage(), e);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Object> handleCardNotFoundException(CardNotFoundException e, WebRequest request) {
        return handleException(AppErrors.CARD_NOT_FOUND, e.getMessage(), e);
    }

    @ExceptionHandler(ConsumerNotFoundException.class)
    public ResponseEntity<Object> handleConsumerNotFoundException(ConsumerNotFoundException e, WebRequest request) {
        return handleException(AppErrors.CONSUMER_NOT_FOUND, e.getMessage(), e);
    }

    @ExceptionHandler(InvalidBalanceException.class)
    public ResponseEntity<Object> handleInvalidBalanceException(InvalidBalanceException e, WebRequest request) {
        return handleException(AppErrors.INVALID_BALANCE, e.getMessage(), e);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(AppErrors.BAD_REQUEST_ERROR, e.getMessage(), e);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(AppErrors.METHOD_NOT_ALLOWED_ERROR, e.getMessage(), e);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return handleException(AppErrors.NOT_FOUND_ERROR, e.getMessage(), e);
    }

    private ResponseEntity<Object> handleException(AppErrors error, String message, Exception e) {
        return handleException(error, new Object[]{message}, e);
    }

    private ResponseEntity<Object> handleException(AppErrors error, Object[] params, Exception e) {
        String message = messageSource.getMessage(error, params);
        logger.error(message, e);

        return ResponseEntity.status(error.getHttpStatus())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BaseResponse.notOk(error.getCode(), message, error.getDetails()));
    }

    private ResponseEntity<Object> handleException(AppErrors error, List<BaseResponse.Error> errors, Exception e) {
        logger.error("Error", e);

        return ResponseEntity.status(error.getHttpStatus())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BaseResponse.notOk(errors));
    }

}
