package br.com.alelo.consumerpat.core.exception;

import java.util.HashMap;

public class ConsumerAlreadyRegisteredException extends BadRequestException {
    public ConsumerAlreadyRegisteredException() {
        this.fieldsAndMessages = new HashMap<>();
        this.fieldsAndMessages.put("document", "invalid.document_already_registered");
    }
}
