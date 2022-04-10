package br.com.alelo.consumer.consumerpat.exception.handler;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alelo.consumer.consumerpat.exception.CardIsExistException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.DocumentIsExistException;
import br.com.alelo.consumer.consumerpat.exception.StandardError;

@ControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler(CardIsExistException.class)
    public ResponseEntity<StandardError> objectNotFound(CardIsExistException e, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
    
    @ExceptionHandler(ConsumerNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ConsumerNotFoundException e, HttpServletRequest request){
    	StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    	
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    
    @ExceptionHandler(DocumentIsExistException.class)
    public ResponseEntity<StandardError> objectNotFound(DocumentIsExistException e, HttpServletRequest request){
    	StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage());
    	
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

}
