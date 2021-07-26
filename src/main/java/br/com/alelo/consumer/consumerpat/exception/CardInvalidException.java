package br.com.alelo.consumer.consumerpat.exception;

public class CardInvalidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CardInvalidException() {
        super("Card not valid.");
    }

}
