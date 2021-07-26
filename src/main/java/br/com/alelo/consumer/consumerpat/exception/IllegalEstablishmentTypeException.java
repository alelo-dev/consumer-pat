package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.util.Constants;

public class IllegalEstablishmentTypeException extends RuntimeException{

    public IllegalEstablishmentTypeException() {
        super(Constants.Errors.INVALID_ESTABLISHMENT_CODE);
    }
}
