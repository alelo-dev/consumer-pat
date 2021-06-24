package br.com.alelo.consumer.consumerpat.exception;

public class ValueInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValueInvalidException() {
		super("Value Invalid.");
	}

}