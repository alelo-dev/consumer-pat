package br.com.alelo.consumer.consumerpat.exception;

public class ExtractNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExtractNotFoundException() {
		super("Extract not found.");
	}

}