package br.com.alelo.consumer.consumerpat.exception;

public class BadRequestException extends Exception {

    private static final long serialVersionUID = -3946078288741435789L;

    public BadRequestException(String message) {
        super(message);
    }

}
