package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.dto.ErrorResponse;

public class InvalidOperationException extends BusinessException{

    public InvalidOperationException(ErrorResponse error){
        super(error);
    }
}