package br.com.alelo.consumer.consumerpat.exceptions;

public class CardNotFoundException extends BussinesException {

	

	public CardNotFoundException() {

		super("Cartão Invalido ou não encontrado");
	}
}
