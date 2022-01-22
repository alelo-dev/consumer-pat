package br.com.alelo.consumer.consumerpat.exceptions;

public class ConsumerNotFoundException extends BussinesException {

    public ConsumerNotFoundException() {

        super("Consumer não encontrado");
    }
}
