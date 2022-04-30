package br.com.alelo.consumer.consumerpat.exception;

public class ConsumerNotFoundException extends NotFoundBaseException{
    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
