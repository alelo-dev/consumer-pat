package br.com.alelo.consumer.consumerpat.exception;

public class CardNumberInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CardNumberInvalidException() {
		super("Card Number Invalid.");
	}

}