package br.com.alelo.consumer.consumerpat.util.exception;

public class EstablishmentNotFoundException extends Exception{

    public EstablishmentNotFoundException(String message) {
        super(message);
    }

    public EstablishmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
