package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerExistException extends RuntimeException {

    public ConsumerExistException() {
    }

    public ConsumerExistException(String message) {
        super(message);
    }

    public ConsumerExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumerExistException(Throwable cause) {
        super(cause);
    }

    public ConsumerExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}