package br.com.alelo.consumer.consumerpat.exceptions;

public class CardAndEstablishmentTypeInvalidException extends BussinesException {

	private static final long serialVersionUID = 1L;

	public CardAndEstablishmentTypeInvalidException() {

		super("Tipo do estabelecimento  e tipo do cartão diferentes");
	}
}
