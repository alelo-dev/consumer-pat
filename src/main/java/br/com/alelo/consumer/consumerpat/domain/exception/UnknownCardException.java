package br.com.alelo.consumer.consumerpat.domain.exception;

public class UnknownCardException extends BusinessException {
    public UnknownCardException() {
        super("unknown card");
    }
}
