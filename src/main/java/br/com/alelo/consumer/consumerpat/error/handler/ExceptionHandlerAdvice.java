package br.com.alelo.consumer.consumerpat.error.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alelo.consumer.consumerpat.exception.BadRequestException;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.exception.StandardError;
import br.com.alelo.consumer.consumerpat.exception.ValidationError;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY.name(), e.getMessage(), request.getRequestURI());
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e,
			HttpServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY.name(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequestException(BadRequestException e, HttpServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.name(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> businessException(BusinessException e, HttpServletRequest request) {
		StandardError error = new StandardError(System.currentTimeMillis(),
				e.getBusinessError().getHttpStatus().value(), e.getBusinessError().getHttpStatus().name(),
				e.getBusinessError().getDescription(), request.getRequestURI());
		return ResponseEntity.status(e.getBusinessError().getHttpStatus()).body(error);
	}
}
