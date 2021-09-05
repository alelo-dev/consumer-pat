package br.com.alelo.consumer.pat.exception;

public class ConsumerNotFoundException extends BusinessException {

    public ConsumerNotFoundException() {
        this("Consumer não encontrado!");
    }

    private ConsumerNotFoundException(final String message) {
        super(message);
    }

}
