package br.com.alelo.consumer.consumerpat.exception;

public class CardIsExistException extends Exception {
	
	private static final long serialVersionUID = -2601007451904587866L;

	public CardIsExistException(String message) {
		super(message);
	}

}
