package br.com.alelo.consumer.consumerpat.infrastructure.exception.types;

import static java.util.Objects.nonNull;

import org.springframework.http.HttpStatus;

public class BusinessException extends DefaultException {

	private static final long serialVersionUID = -3253005617045010385L;
	private final ErrorMessageDto errors;

	public BusinessException(final String message) {
		super(message);
		this.errors = null;
	}

	public BusinessException(final String message, ErrorMessageDto errors) {
		super(message);
		this.errors = errors;
	}

	public HttpStatus getStatusCode() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}

	@Override
	public ErrorMessageDto buildResponse() {
		if (nonNull(errors)) {
			return errors;
		}
		return super.buildResponse();
	}

}