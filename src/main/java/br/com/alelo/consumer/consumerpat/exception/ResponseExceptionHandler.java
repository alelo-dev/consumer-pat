package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ BusinesException.class })
	public ResponseEntity<Object> handleBusinesException(final BusinesException e) {
		return ResponseEntity.status(e.getResponse().getStatus()).body(e.getResponse());
	}

}
