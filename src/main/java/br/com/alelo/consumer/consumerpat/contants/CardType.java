package br.com.alelo.consumer.consumerpat.contants;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Collections;
import java.util.Set;

public enum CardType {
    FOOD_CARD,
    DRUGSTORE_CARD,
    FUEL_CARD;

    @JsonValue
    public String toValue() {
        return toString();
    }
}
