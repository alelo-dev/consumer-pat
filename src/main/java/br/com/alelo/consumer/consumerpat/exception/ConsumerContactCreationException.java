package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.util.Constants;

public class ConsumerContactCreationException extends RuntimeException{

    public ConsumerContactCreationException() {
        super(Constants.Errors.SAVE_UPDATE_CONTACT_CUSTOMER);
    }
}
