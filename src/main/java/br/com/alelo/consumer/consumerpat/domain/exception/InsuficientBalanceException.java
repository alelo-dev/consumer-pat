package br.com.alelo.consumer.consumerpat.domain.exception;

import br.com.alelo.consumer.consumerpat.domain.entity.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsuficientBalanceException extends Exception{

    public InsuficientBalanceException(ErrorMessage message) {
        super(message.getMessage());
    }
}
