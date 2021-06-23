package br.com.alelo.consumer.consumerpat.exception;

public class CardTypeInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2840127117825836261L;

	public CardTypeInvalidException() {
		super("Card Type Invalid.");
	}

}