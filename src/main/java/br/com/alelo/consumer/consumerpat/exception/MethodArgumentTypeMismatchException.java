package br.com.alelo.consumer.consumerpat.exception;

/**
 * @author Guilherme de Castro
 * @created 01/09/2021 - 14:26
 */
public class MethodArgumentTypeMismatchException extends RuntimeException{

    public MethodArgumentTypeMismatchException(final String message) {
        super(message);
    }
}
