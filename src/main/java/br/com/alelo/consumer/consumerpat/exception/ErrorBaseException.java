package br.com.alelo.consumer.consumerpat.exception;

public class ErrorBaseException extends RuntimeException{
    public ErrorBaseException(String message) {
        super(message);
    }
}
