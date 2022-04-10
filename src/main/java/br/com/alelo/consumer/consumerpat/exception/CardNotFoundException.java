package br.com.alelo.consumer.consumerpat.exception;

public class CardNotFoundException extends Exception {
	
	private static final long serialVersionUID = -2601007451904587866L;

	public CardNotFoundException(String message) {
		super(message);
	}

}
