package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.dto.ErrorResponse;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private ErrorResponse error;

    public BusinessException (ErrorResponse error){
        this.error = error;
    }

}
