package br.com.alelo.consumer.consumerpat.util.exception;

public class CardNotFoundException extends Exception{

    public CardNotFoundException(String message) {
        super(message);
    }

    public CardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
