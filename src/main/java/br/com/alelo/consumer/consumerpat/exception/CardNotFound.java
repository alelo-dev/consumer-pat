package br.com.alelo.consumer.consumerpat.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CardNotFound extends RuntimeException {
    public CardNotFound(String message) {
        super(message);
    }
}
