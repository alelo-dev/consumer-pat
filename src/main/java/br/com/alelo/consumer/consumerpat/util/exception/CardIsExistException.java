package br.com.alelo.consumer.consumerpat.util.exception;

public class CardIsExistException extends Exception{
    public CardIsExistException(String message) {
        super(message);
    }

    public CardIsExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
