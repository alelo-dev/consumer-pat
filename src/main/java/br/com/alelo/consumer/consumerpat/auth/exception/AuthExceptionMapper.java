package br.com.alelo.consumer.consumerpat.auth.execeptions;

import br.com.alelo.consumer.consumerpat.config.exception.ExceptionDefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RestController
public class AuthExceptionMapper extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionDefaultResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionDefaultResponse exceptionDefaultResponse = new ExceptionDefaultResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false)
		);
		return new ResponseEntity<>(exceptionDefaultResponse, INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AuthLogoutException.class)
	public final ResponseEntity<ExceptionDefaultResponse> handleLogoutException(Exception ex, WebRequest request) {
		ExceptionDefaultResponse exceptionDefaultResponse = new ExceptionDefaultResponse(
			new Date(),
			ex.getMessage(),
			request.getDescription(false)
		);
		return new ResponseEntity<>(exceptionDefaultResponse, GONE);
	}
}