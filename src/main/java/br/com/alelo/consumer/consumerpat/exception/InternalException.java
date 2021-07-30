package br.com.alelo.consumer.consumerpat.exception;

import lombok.Getter;

@Getter
public class InternalException extends RuntimeException {
    private String details;

    public InternalException(String message, String details) {
        super(message);
        this.details = details;
    }

    public InternalException(String message, Throwable cause, String details) {
        super(message, cause);
        this.details = details;
    }
}
