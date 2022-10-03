package br.com.alelo.consumer.consumerpat.exceptions;

public class CardTypeEnumsNotFoundException extends BussinesException {

	private static final long serialVersionUID = 1L;

	public CardTypeEnumsNotFoundException() {

		super("Tipo de cartâo inválido");
	}

}
