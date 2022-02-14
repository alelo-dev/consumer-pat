package br.com.alelo.consumer.consumerpat.service.exception;

public class ConsumerException extends Exception {
    public ConsumerException(String msg) {
        super(msg);
    }

    public ConsumerException(String msg, Object... args) {
        super(String.format(msg, args));
    }
}
