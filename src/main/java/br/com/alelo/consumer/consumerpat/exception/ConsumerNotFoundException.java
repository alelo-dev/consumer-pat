package br.com.alelo.consumer.consumerpat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ConsumerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8564930097448464390L;

	public ConsumerNotFoundException(Integer id) {
		super("Consumer not found with id: " + id);
	}
	
}
