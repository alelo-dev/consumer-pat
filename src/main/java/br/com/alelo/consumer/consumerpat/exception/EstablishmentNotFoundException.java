package br.com.alelo.consumer.consumerpat.exception;

public class EstablishmentNotFoundException extends RuntimeException {

    private String name;

    public EstablishmentNotFoundException(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
