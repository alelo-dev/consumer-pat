package br.com.alelo.consumer.consumerpat.common.domain;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
