package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.dto.ErrorResponse;

public class IllegalParameterException extends BusinessException{

    public IllegalParameterException(ErrorResponse error){
        super(error);
    }
}
