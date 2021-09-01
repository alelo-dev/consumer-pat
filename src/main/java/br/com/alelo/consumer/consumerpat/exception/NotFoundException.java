package br.com.alelo.consumer.consumerpat.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2720948316979517800L;

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(final String message) {
        super(message);
    }

}
