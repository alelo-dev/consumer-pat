package br.com.alelo.consumer.consumerpat.exception;

import java.util.HashMap;

public class ConsumerEmptyException extends BadRequestException {

    public ConsumerEmptyException() {
        this.fieldsAndMessages = new HashMap<>();
        this.fieldsAndMessages.put("consumer", "invalid.item");
    }
}
