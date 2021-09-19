package br.com.alelo.consumer.consumerpat.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.BusinessException;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.ErrorMessageDto;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.NotFoundException;

@ControllerAdvice
public class ApiExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {

	private static Logger log = LoggerFactory.getLogger(ApiExceptionHandlerConfiguration.class);

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorMessageDto> notFoundError(NotFoundException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseEntity.status(ex.getStatusCode()).body(ex.buildResponse());
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorMessageDto> businessError(BusinessException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseEntity.status(ex.getStatusCode()).body(ex.buildResponse());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorMessageDto> internalServerError(Exception ex) {

		ErrorMessageDto error = new ErrorMessageDto(ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}