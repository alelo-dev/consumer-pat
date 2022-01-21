package br.com.alelo.consumer.consumerpat.exceptions;

public class ConsumerValidation extends BussinesException {

    public ConsumerValidation() {
        super("Falha na validação do CreateConsumerDTO");
    }
}
