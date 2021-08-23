package br.com.alelo.consumer.consumerpat.core.exception;

import java.util.HashMap;

public class CardAlreadyRegisteredException extends BadRequestException {

    public CardAlreadyRegisteredException() {
        this.fieldsAndMessages = new HashMap<>();

        this.fieldsAndMessages.put("card", "invalid.card_already_registered");
    }
}
