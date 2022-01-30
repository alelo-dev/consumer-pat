package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.entity.dto.ErrorDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class BusinessException extends Exception{

    private List<ErrorDTO> errorList;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, List<ErrorDTO> errorList){
        super(message);
        this.errorList = errorList;
    }

}
