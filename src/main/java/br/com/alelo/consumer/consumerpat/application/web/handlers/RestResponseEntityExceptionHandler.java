package br.com.alelo.consumer.consumerpat.application.web.handlers;

import br.com.alelo.consumer.consumerpat.domain.exceptions.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ConsumersNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exceptions.ExtractNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CardNotFoundException.class, ConsumerNotFoundException.class,
            ConsumersNotFoundException.class, ExtractNotFoundException.class
    })
    public ResponseEntity<Object> handleEntityNotFound(Exception ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}