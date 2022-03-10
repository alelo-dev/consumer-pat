package br.com.alelo.consumer.consumerpat.exception;

public class ValidationConsumer extends Exception{

    public ValidationConsumer(String errorMessage) {
        super(errorMessage);
    }

}
