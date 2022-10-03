package br.com.alelo.consumer.consumerpat.exceptions;

public class CardNumberAlreadyExistsException extends BussinesException {

	private static final long serialVersionUID = 1L;

	public CardNumberAlreadyExistsException() {

		super("Cartâo já existe");
	}

}
