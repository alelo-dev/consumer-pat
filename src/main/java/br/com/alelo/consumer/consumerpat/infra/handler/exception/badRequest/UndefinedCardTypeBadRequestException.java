package br.com.alelo.consumer.consumerpat.infra.handler.exception.badRequest;


public class UndefinedCardTypeBadRequestException extends BadRequestException {

    public UndefinedCardTypeBadRequestException(final String msg) {
        super(msg);
    }

}
