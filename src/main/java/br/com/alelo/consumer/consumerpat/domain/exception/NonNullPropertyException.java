package br.com.alelo.consumer.consumerpat.domain.exception;

public class NonNullPropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NonNullPropertyException(String msg) {
		super(msg);
	}
}
