package br.com.alelo.consumer.consumerpat.exception;

public class CardNumberInvalidEstablishmentTypeException extends RuntimeException {

	private static final long serialVersionUID = 8901628262749562851L;

	public CardNumberInvalidEstablishmentTypeException(final String message) {
		super(message);
	}

}