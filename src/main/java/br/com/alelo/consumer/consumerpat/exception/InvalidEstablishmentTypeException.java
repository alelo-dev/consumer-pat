package br.com.alelo.consumer.consumerpat.exception;

public class InvalidEstablishmentTypeException extends RuntimeException {
    public InvalidEstablishmentTypeException() {
        super("Invalid establishment type");
    }
}
