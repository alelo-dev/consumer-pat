package br.com.alelo.consumer.consumerpat.domain.exception;

public class InvalidIdException extends BusinessException {
    public InvalidIdException() {
        super("invalid id");
    }
}
