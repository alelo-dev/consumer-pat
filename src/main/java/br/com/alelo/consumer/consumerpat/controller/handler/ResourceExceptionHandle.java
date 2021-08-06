package br.com.alelo.consumer.consumerpat.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alelo.consumer.consumerpat.CardNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandle {

	@ExceptionHandler(CardNotFoundException.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(CardNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
}
