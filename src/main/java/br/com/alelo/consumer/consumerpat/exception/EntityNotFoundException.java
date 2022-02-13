package br.com.alelo.consumer.consumerpat.exception;

import br.com.alelo.consumer.consumerpat.dto.ErrorResponse;
import lombok.AllArgsConstructor;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(ErrorResponse error){
        super(error);
    }
}
