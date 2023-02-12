package br.com.alelo.consumer.consumerpat.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorDetails> serviceExceptionHandler(ServiceException ex) {
        ErrorDetails errorModel = new ErrorDetails(ex.getMessage());
        log.error(ex);
        return new ResponseEntity <ErrorDetails> (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceWarningException.class)
    public ResponseEntity <ErrorDetails> warningExcpetionHandler(ServiceWarningException ex) {
        ErrorDetails errorModel = new ErrorDetails(ex.getMessage());
        log.warn(ex);
        return new ResponseEntity < > (errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity <ErrorDetails> notFoundExcpetionHandler(NotFoundException ex) {
        ErrorDetails errorModel = new ErrorDetails(ex.getMessage());
        log.warn(ex);
        return new ResponseEntity < > (errorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity <ErrorDetails> insufficientBalanceHandler(InsufficientBalanceException ex) {
        ErrorDetails errorModel = new ErrorDetails(ex.getMessage());
        log.warn(ex);
        return new ResponseEntity < > (errorModel, HttpStatus.BAD_REQUEST);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation error. Check 'errors' field for details."
        );

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

}