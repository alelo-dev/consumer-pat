package br.com.alelo.consumer.consumerpat.exceptions;

public class CardNumberException extends BussinesException {

    public CardNumberException() {

        super("cardNumber já existe");
    }
}
