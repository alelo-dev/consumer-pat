package br.com.alelo.consumer.consumerpat.domain.exception;

import br.com.alelo.consumer.consumerpat.domain.entity.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BalanceChangeException extends Exception {

    public BalanceChangeException(ErrorMessage message) {
        super(message.getMessage());
    }
}
