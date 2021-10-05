package br.com.alelo.consumer.consumerpat.domain.exception;

public class EntityExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityExistsException(String msg) {
		super(msg);
	}
}
