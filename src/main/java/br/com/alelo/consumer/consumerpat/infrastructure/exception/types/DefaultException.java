package br.com.alelo.consumer.consumerpat.infrastructure.exception.types;

import org.springframework.http.HttpStatus;

public abstract class DefaultException extends RuntimeException {

	private static final long serialVersionUID = 4220212442387105196L;

	protected DefaultException() {
		this(null, null);
	}

	protected DefaultException(final String message) {
		super(message);
	}

	protected DefaultException(final Throwable cause) {
		super(cause);
	}

	protected DefaultException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ErrorMessageDto buildResponse() {
		final ErrorMessageDto responseDefault = new ErrorMessageDto();
		responseDefault.setMessage(getMessage());
		return responseDefault;
	}

	public abstract HttpStatus getStatusCode();

}