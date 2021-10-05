package br.com.alelo.consumer.consumerpat.domain.exception;

public class EntityInUseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityInUseException(String msg) {
		super(msg);
	}
}
