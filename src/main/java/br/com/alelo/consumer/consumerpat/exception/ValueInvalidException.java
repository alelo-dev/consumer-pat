package br.com.alelo.consumer.consumerpat.exception;

public class ValueInvalidException extends RuntimeException {

	private static final long serialVersionUID = 8901628262749562851L;

	public ValueInvalidException() {
		super("Value Invalid.");
	}

}