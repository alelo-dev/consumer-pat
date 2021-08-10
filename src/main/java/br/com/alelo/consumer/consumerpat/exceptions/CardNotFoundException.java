package br.com.alelo.consumer.consumerpat.exceptions;

public class CardNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CardNotFoundException(String msg) {
        super(msg);
    }

    public CardNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
