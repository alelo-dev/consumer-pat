package br.com.alelo.consumer.consumerpat.exception;

public class CannotCreateExistingConsumerException extends RuntimeException {
    public CannotCreateExistingConsumerException() {
        super("Cannot create existing consumer");
    }
}
