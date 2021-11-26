package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception ex) {
		if (ex instanceof BusinessException) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Oh oh um erro inesperado ocorreu :( !", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}