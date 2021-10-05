package br.com.alelo.consumer.consumerpat.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.alelo.consumer.consumerpat.domain.exception.EntityExistsException;
import br.com.alelo.consumer.consumerpat.domain.exception.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.exception.ExceededException;
import br.com.alelo.consumer.consumerpat.domain.exception.NonNullPropertyException;

//@ControllerAdvice faz com que todas as controllers sigam o modelo do @ExceptionHandler modelado aqui
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	// RFC 7807
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex, WebRequest wreq){
		ExceptionType exceptionType = ExceptionType.ENTITY_NOT_FOUND;
		String detail = ex.getMessage();
		ExceptionDetail problem = getExceptionDetailBuilder(exceptionType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(),exceptionType.getStatus(), wreq);
	}
	
	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<?> handleEntityExists(EntityExistsException ex, WebRequest wreq){
		ExceptionType exceptionType = ExceptionType.ENTITY_EXISTS;
		String detail = ex.getMessage();
		ExceptionDetail problem = getExceptionDetailBuilder(exceptionType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(),exceptionType.getStatus(), wreq);
	}
	
	@ExceptionHandler(NonNullPropertyException.class)
	public ResponseEntity<?> handleNonNullProperty(NonNullPropertyException ex, WebRequest wreq){
		ExceptionType exceptionType = ExceptionType.INVALID_BODY;
		String detail = ex.getMessage();
		ExceptionDetail problem = getExceptionDetailBuilder(exceptionType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(),exceptionType.getStatus(), wreq);
	}
	
	@ExceptionHandler(ExceededException.class)
	public ResponseEntity<?> handleExceededException(ExceededException ex, WebRequest wreq){
		ExceptionType exceptionType = ExceptionType.EXCEEDED_VALUE;
		String detail = ex.getMessage();
		ExceptionDetail problem = getExceptionDetailBuilder(exceptionType, detail).build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(),exceptionType.getStatus(), wreq);
	}
	
	// Métodos Auxiliares
	private ExceptionDetail.ExceptionDetailBuilder getExceptionDetailBuilder(ExceptionType exceptionType, String detail){
		return ExceptionDetail.builder()
				.status(exceptionType.getStatus().value())
				.type(exceptionType.getPath())
				.title(exceptionType.getTitle())
				.detail(detail);
	}
}
