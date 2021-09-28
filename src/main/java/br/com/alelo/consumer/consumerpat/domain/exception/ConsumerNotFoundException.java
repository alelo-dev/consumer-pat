package br.com.alelo.consumer.consumerpat.domain.exception;

import br.com.alelo.consumer.consumerpat.domain.entity.enums.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConsumerNotFoundException  extends Exception{

    public ConsumerNotFoundException(ErrorMessage message) {
        super(message.getMessage());
    }
}
