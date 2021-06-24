package br.com.alelo.consumer.consumerpat.exception;

public class ValueInvalidException extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public ValueInvalidException() {
		super("Value Invalid.");
	}

}