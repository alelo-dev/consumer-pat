package br.com.alelo.consumer.consumerpat.exception;

public class CardNumberInvalidEstablishmentTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CardNumberInvalidEstablishmentTypeException(final String message) {
		super(message);
	}

}