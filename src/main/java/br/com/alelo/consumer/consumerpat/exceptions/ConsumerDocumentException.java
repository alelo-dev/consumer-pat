package br.com.alelo.consumer.consumerpat.exceptions;

public class ConsumerDocumentException extends BussinesException {

    public ConsumerDocumentException() {

        super("documentNumber já existe");
    }
}
