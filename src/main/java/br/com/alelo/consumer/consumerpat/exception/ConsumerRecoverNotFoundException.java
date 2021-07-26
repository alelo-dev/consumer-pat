package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.util.Constants;

public class ConsumerRecoverNotFoundException extends RuntimeException{

    public ConsumerRecoverNotFoundException() {
        super(Constants.Errors.RECOVER_CUSTOMER);
    }
}
