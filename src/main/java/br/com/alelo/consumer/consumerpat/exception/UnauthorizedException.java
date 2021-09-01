package br.com.alelo.consumer.consumerpat.exception;

/**
 * @author Guilherme de Castro
 * @created 31/08/2021 - 10:52
 */

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(final String message) {
        super(message);
    }

}
