package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.util.Constants;

public class ConsumerCreationException extends RuntimeException{

    public ConsumerCreationException(Boolean isUpdate) {
        super(isUpdate ? Constants.Errors.CREATE_CUSTOMER : Constants.Errors.UPDATE_CUSTOMER);
    }
}
