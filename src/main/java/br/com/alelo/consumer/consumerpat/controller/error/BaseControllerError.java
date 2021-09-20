package br.com.alelo.consumer.consumerpat.controller.error;

import br.com.alelo.consumer.consumerpat.utils.exception.ProcessException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Order(0)
public class BaseControllerError {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<ErrorMessage> exception(final ProcessException ex) {
    return ResponseEntity.badRequest().body(
        ErrorMessage.builder()
            .message(ex.getMessage())
            .build());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorMessage> exception(final EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorMessage.builder()
                    .message(ex.getMessage())
                    .build());
  }
}
