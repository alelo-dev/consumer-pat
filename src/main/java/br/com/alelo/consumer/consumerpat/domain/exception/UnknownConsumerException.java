package br.com.alelo.consumer.consumerpat.domain.exception;

public class UnknownConsumerException extends BusinessException {
    public UnknownConsumerException() {
        super("unknown consumer");
    }
}
