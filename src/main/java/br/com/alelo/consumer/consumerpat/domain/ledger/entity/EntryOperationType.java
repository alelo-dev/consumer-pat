package br.com.alelo.consumer.consumerpat.domain.ledger.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EntryOperationType {

    CREDIT,
    DEBIT;

    @JsonValue
    public String value() {
        return name();
    }

    @JsonCreator
    public static EntryOperationType fromString(String value) {
        return EntryOperationType.valueOf(value.toUpperCase());
    }
}
