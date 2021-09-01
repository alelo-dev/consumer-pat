package br.com.alelo.consumer.consumerpat.exception;


/**
 * @author Guilherme de Castro
 * @created 31/08/2021 - 10:52
 */


public class BadGatewayException extends RuntimeException {

    public BadGatewayException(final String message) {
        super(message);
    }

    public BadGatewayException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
