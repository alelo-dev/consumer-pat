package br.com.alelo.consumer.consumerpat.domain.payment.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstablishmentType {
    FOOD,
    DRUGSTORE,
    FUEL;

    @JsonValue
    public String value() {
        return name();
    }

    @JsonCreator
    public static EstablishmentType fromString(String value) {
        return EstablishmentType.valueOf(value.toUpperCase());
    }
}
