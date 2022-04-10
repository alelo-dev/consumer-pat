package br.com.alelo.consumer.consumerpat.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alelo.consumer.consumerpat.exception.BalanceException;
import br.com.alelo.consumer.consumerpat.exception.CardIsExistException;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.DocumentIsExistException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotAcceptCardException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.StandardError;

@ControllerAdvice
public class ControllersExceptionHandler {

	@ExceptionHandler(CardIsExistException.class)
	public ResponseEntity<StandardError> objectConflict(CardIsExistException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler(ConsumerNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ConsumerNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DocumentIsExistException.class)
	public ResponseEntity<StandardError> objectConflict(DocumentIsExistException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler(CardNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(CardNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(CardTypeNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(CardTypeNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(EstablishmentNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(EstablishmentNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(BalanceException.class)
	public ResponseEntity<StandardError> objectNotAcceptable(BalanceException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
	}

	@ExceptionHandler(EstablishmentNotAcceptCardException.class)
	public ResponseEntity<StandardError> objectNotAcceptable(EstablishmentNotAcceptCardException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
	}

}
