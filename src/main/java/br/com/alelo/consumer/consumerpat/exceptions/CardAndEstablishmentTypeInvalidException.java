package br.com.alelo.consumer.consumerpat.exceptions;

public class CardAndEstablishmentTypeInvalidException extends BussinesException {

    public CardAndEstablishmentTypeInvalidException() {

        super("Tipo do cartão e tipo do estabelecimento diferentes");
    }
}
