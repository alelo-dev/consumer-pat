package br.com.alelo.consumer.consumerpat.exception;

public class MoreThanOneEstablishmentFoundException extends RuntimeException {

    private String name;

    public MoreThanOneEstablishmentFoundException(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
