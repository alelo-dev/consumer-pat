package br.com.alelo.consumer.consumerpat.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodeEnum {

    ACCESS_DENIED("ACCESS_DENIED"),
    CONSUMER_NOT_FOUND("CONSUMER_NOT_FOUND"),
    INVALID_PARAMETER("INVALID_PARAMETER"),
    TOO_MANY_REQUESTS("TOO_MANY_REQUESTS"),
    UNEXPECTED_ERROR("UNEXPECTED_ERROR"),
    VALIDATION_ERROR("VALIDATION_ERROR"),
    UNAUTHORIZED("UNAUTHORIZED"),
    NOT_FOUND("NOT_FOUND");

    private final String value;

    ErrorCodeEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ErrorCodeEnum fromValue(String text) {
        for (ErrorCodeEnum b : ErrorCodeEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}
