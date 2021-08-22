package br.com.alelo.consumerpat.core.exception;

import java.util.HashMap;

public class InvalidEstablishmentForCardException extends BadRequestException {

    public InvalidEstablishmentForCardException() {
        this.fieldsAndMessages = new HashMap<>();
        this.fieldsAndMessages.put("type", "invalid.establishment_type_for_card");
    }
}
