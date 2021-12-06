package br.com.alelo.consumer.consumerpat.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.IncompatibleCardTypeException;
import br.com.alelo.consumer.consumerpat.exception.InvalidBalanceValueException;
import br.com.alelo.consumer.consumerpat.exception.InvalidConsumerException;
import br.com.alelo.consumer.consumerpat.exception.InvalidPurchaseOccurrenceException;

@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(value = CardNotFoundException.class)
  public ResponseEntity<Object> handleCardNotFoundException(CardNotFoundException ex) {
    return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
  }
  
  @ExceptionHandler(value = IncompatibleCardTypeException.class)
  public ResponseEntity<Object> handleCardTypeNotFoundException(IncompatibleCardTypeException ex) {
    return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
  }
  
  @ExceptionHandler(value = InvalidBalanceValueException.class)
  public ResponseEntity<Object> handleInvalidBalanceValueException(InvalidBalanceValueException ex) {
    return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
  }
  
  @ExceptionHandler(value = InvalidConsumerException.class)
  public ResponseEntity<Object> handleInvalidConsumerException(InvalidConsumerException ex) {
    return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
  }
  
  @ExceptionHandler(value = InvalidPurchaseOccurrenceException.class)
  public ResponseEntity<Object> handleIInvalidPurchaseOccurrenceException(InvalidPurchaseOccurrenceException ex) {
    return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
  }

}
