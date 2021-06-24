package br.com.alelo.consumer.consumerpat.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.alelo.consumer.consumerpat.model.exception.ErrorMessage;


@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage notFoundException(NotFoundException ex, WebRequest request) {
		ErrorMessage message = ErrorMessage.builder()
		.statusCode( HttpStatus.NOT_FOUND.value())
		.timestamp(LocalDateTime.now())
		.message(ex.getMessage())
		.description(request.getDescription(false))
		.build();
    
		return message;
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage badRequestException(BadRequestException ex, WebRequest request) {
		ErrorMessage message = ErrorMessage.builder()
		.statusCode( HttpStatus.NOT_FOUND.value())
		.timestamp(LocalDateTime.now())
		.message(ex.getMessage())
		.description(request.getDescription(false))
		.build();
    
		return message;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage exceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = ErrorMessage.builder()
			.statusCode( HttpStatus.INTERNAL_SERVER_ERROR.value())
			.timestamp(LocalDateTime.now())
			.message(ex.getMessage())
			.description(request.getDescription(false))
			.build();
    
		return message;
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage runtimeExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = ErrorMessage.builder()
			.statusCode( HttpStatus.INTERNAL_SERVER_ERROR.value())
			.timestamp(LocalDateTime.now())
			.message(ex.getMessage())
			.description(request.getDescription(false))
			.build();
    
		return message;
	}
	
}