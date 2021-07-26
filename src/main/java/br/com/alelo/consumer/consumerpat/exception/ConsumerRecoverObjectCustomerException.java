package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.util.Constants;

public class ConsumerRecoverObjectCustomerException extends RuntimeException{

    public ConsumerRecoverObjectCustomerException() {
        super(Constants.Errors.RECOVER_RECORD_CONSUMER);
    }
}
