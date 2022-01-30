package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.entity.dto.ErrorDTO;

import java.util.List;

public class BusinessException extends Exception{

    private List<ErrorDTO> errorList;

    public BusinessException(List<ErrorDTO> errorList){
        super();
        this.errorList = errorList;
    }

    public BusinessException(String message, List<ErrorDTO> errorList){
        super(message);
        this.errorList = errorList;
    }


    public BusinessException(String message){
        super(message);
    }

}
