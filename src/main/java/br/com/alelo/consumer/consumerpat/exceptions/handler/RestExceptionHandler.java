package br.com.alelo.consumer.consumerpat.exceptions.handler;

import br.com.alelo.consumer.consumerpat.exceptions.BaseException;
import br.com.alelo.consumer.consumerpat.exceptions.Fault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public final ResponseEntity<Object> handleBaseException(final BaseException ex) {
        logger.error(ex.getErrorMessage(), ex);

        Fault exceptionResponse = Fault.builder()
                .code(ex.getErrorCodeEnum())
                .message(ex.getErrorMessage())
                .details(ex.getMessage())
                .build();

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(exceptionResponse);
    }

}
