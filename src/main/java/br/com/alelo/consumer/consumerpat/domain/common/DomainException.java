package br.com.alelo.consumer.consumerpat.domain.common;

public class DomainException extends RuntimeException {
    public DomainException(final String message) {
        super(message);
    }
}
