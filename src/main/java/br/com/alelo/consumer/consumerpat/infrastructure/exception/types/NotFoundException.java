package br.com.alelo.consumer.consumerpat.infrastructure.exception.types;

import org.springframework.http.HttpStatus;

public class NotFoundException extends DefaultException {

	private static final long serialVersionUID = -3253005617045010386L;

	public NotFoundException(final String message) {
		super(message);
	}

	public HttpStatus getStatusCode() {
		return HttpStatus.NOT_FOUND;
	}

}