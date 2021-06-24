package br.com.alelo.consumer.consumerpat.exception;

public class CardNumberAlreadyRegisteredException extends BadRequestException {

	private static final long serialVersionUID = 1L;

	public CardNumberAlreadyRegisteredException() {
		super("Document number already registered.");
	}

}