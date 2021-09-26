package br.com.alelo.consumer.consumerpat.exception;

public class RestNotFoundException extends RuntimeException {

    public RestNotFoundException() {
    }

    public RestNotFoundException(String message) {
        super(message);
    }

    public RestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestNotFoundException(Throwable cause) {
        super(cause);
    }

    public RestNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
