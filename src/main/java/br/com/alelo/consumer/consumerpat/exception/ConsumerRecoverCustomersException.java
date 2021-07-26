package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.util.Constants;

public class ConsumerRecoverCustomersException extends RuntimeException{

    public ConsumerRecoverCustomersException() {
        super(Constants.Errors.RECOVER_ALL_CUSTOMERS);
    }
}
