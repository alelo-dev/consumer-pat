package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerNotFoundException extends RuntimeException {

    public ConsumerNotFoundException() {
        super("Consumer not found");
    }
}
