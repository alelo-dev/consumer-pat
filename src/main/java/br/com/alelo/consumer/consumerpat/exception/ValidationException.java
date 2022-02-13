package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.dto.ErrorResponse;

public class ValidationException extends BusinessException{

    public ValidationException(ErrorResponse error){
        super(error);
    }
}