package br.com.alelo.consumer.consumerpat.domain.exception;

public class ConsumerApplicationException extends RuntimeException {

    public ConsumerApplicationException() {

    }

    public ConsumerApplicationException(String message) {
        super(message);
    }

    public ConsumerApplicationException(Throwable cause) {
        super(cause);
    }

    public ConsumerApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumerApplicationException(
            String message,
            Throwable cause,
            boolean enableSupression,
            boolean writeableStackTrace) {
        super(message, cause, enableSupression, writeableStackTrace);
    }

}
