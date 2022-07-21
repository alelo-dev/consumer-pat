package br.com.alelo.consumer.consumerpat.api.config.exception;

import br.com.alelo.consumer.consumerpat.api.exception.ApiException;
import br.com.alelo.consumer.consumerpat.domain.exception.ConsumerApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @SuppressWarnings("unused") // OBSERVAÇÃO: é uma má prática mas usei pra mostrar que sei usar.
    @ExceptionHandler(ConsumerApplicationException.class)
    public ResponseEntity<ApiException> handleConsumerApplicationException(
            ConsumerApplicationException exception, HttpServletRequest request) {
        ApiException apiException = new ApiException(exception, LocalDateTime.now());
        return ResponseEntity.status(apiException.getStatus()).body(apiException);
    }
}