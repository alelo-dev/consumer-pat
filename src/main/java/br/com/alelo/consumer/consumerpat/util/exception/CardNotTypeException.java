package br.com.alelo.consumer.consumerpat.util.exception;

public class CardNotTypeException extends Exception{

    public CardNotTypeException(String message) {
        super(message);
    }

    public CardNotTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
