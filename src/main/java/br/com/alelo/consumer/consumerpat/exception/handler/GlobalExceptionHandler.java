package br.com.alelo.consumer.consumerpat.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.alelo.consumer.consumerpat.domain.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGenericErrors(Exception ex, WebRequest request) {
		ex.printStackTrace();
		
		var status = 500;
		
		if(ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
			status = ex.getClass().getAnnotation(ResponseStatus.class).code().value();
		}
		
		return ResponseEntity.status(status).body(
				ApiErrorResponse
				.builder()
				.timestamp(LocalDateTime.now())
				.message(ex.getMessage())
				.build());
	}
	
}
