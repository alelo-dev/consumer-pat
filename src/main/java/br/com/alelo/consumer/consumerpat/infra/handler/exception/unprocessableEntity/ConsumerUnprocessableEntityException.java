package br.com.alelo.consumer.consumerpat.infra.handler.exception.unprocessableEntity;

public class ConsumerUnprocessableEntityException extends UnprocessableEntityException {

    public ConsumerUnprocessableEntityException(String msg) {
        super(msg);
    }
}
