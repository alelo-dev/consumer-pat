package br.com.alelo.consumerpat.core.exception;

import java.util.Map;

public class BadRequestException extends Exception {

    protected Map<String, String> fieldsAndMessages;

    public BadRequestException() {}

    public BadRequestException(Map<String, String> fieldsAndMessages) {
        this.fieldsAndMessages = fieldsAndMessages;
    }
}
