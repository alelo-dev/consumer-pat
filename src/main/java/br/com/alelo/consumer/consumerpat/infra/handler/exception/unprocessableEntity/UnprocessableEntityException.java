package br.com.alelo.consumer.consumerpat.infra.handler.exception.unprocessableEntity;

import br.com.alelo.consumer.consumerpat.infra.handler.exception.ApiException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class UnprocessableEntityException extends ApiException {
    protected UnprocessableEntityException(String msg) {
        super(msg, UNPROCESSABLE_ENTITY.value());
    }
}
