package br.com.alelo.consumer.consumerpat.exception;

public class DuplicatedRegistry extends RuntimeException {
    private static final long serialVersionUID = 6210964506339092884L;

    public DuplicatedRegistry(final String message) {
        super(message);
    }
}