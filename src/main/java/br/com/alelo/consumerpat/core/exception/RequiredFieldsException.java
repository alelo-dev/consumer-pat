package br.com.alelo.consumerpat.core.exception;

import java.util.Map;

public class RequiredFieldsException extends BadRequestException {
    public RequiredFieldsException(Map<String, String> fieldsAndMessages) {
        super(fieldsAndMessages);
    }
}
