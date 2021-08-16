package br.com.alelo.consumer.consumerpat.exception.handler;

import org.hibernate.TransientPropertyValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.alelo.consumer.consumerpat.exception.BalanceNotEnoughException;
import br.com.alelo.consumer.consumerpat.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.model.ErrorResponseModel;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponseModel> cardNotFound(EntityNotFoundException exception) {
		log.error(exception.getMessage());
		return notProcessable(HttpStatus.NOT_FOUND, exception.getMessage());
	}
	
	
	@ExceptionHandler(BalanceNotEnoughException.class)
	public ResponseEntity<ErrorResponseModel> balanceNotEnouth(BalanceNotEnoughException exception) {
		log.error(exception.getMessage());
		return notProcessable(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(TransientPropertyValueException.class)
	public ResponseEntity<ErrorResponseModel> transientPropertyValueException(TransientPropertyValueException exception) {
		log.error(exception.getMessage(), exception);
		String[] entity = exception.getMessage().split("->");
		return notProcessable(HttpStatus.BAD_REQUEST, 
				String.format("Favor validar os dados informados para entidade %s", entity[1]));
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponseModel> dataIntegrityViolationException(DataIntegrityViolationException exception) {
		log.error("Tivemos um erro de violação de integridade, favor verificar, número do cartão e categoria", exception);		
		return notProcessable(HttpStatus.BAD_REQUEST, "Tivemos um erro de violação de integridade, favor verificar, número do cartão e categoria");
	}	
	

	
	private ResponseEntity<ErrorResponseModel> notProcessable(HttpStatus httpStatus, String message) {

		return ResponseEntity.status(httpStatus).body(ErrorResponseModel.builder()
														.code(String.valueOf(httpStatus))
														.message(message).build());						
	}
}
