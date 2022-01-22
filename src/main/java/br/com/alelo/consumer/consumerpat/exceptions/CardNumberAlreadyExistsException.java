package br.com.alelo.consumer.consumerpat.exceptions;

public class CardNumberAlreadyExistsException extends BussinesException {

    public CardNumberAlreadyExistsException() {

        super("cardNumber já existe");
    }
}
