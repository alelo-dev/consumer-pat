package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.util.Constants;

public class ExtractCreationException extends RuntimeException{

    public ExtractCreationException() {
        super(Constants.Errors.CREATE_EXTRACT);
    }
}
