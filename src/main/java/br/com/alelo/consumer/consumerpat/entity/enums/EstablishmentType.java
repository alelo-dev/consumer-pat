package br.com.alelo.consumer.consumerpat.entity.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.Set;

@Getter
public enum EstablishmentType {
    FOOD(1, "FoodStore", Collections.singleton(CardType.FOOD_CARD)),
    DRUG_STORE(2, "DrugStore", Collections.singleton(CardType.DRUG_STORE)),
    FUEL(3, "FuelStore", Collections.singleton(CardType.FUEL_CARD));

    private final int code;
    private final String description;
    private final Set<CardType> acceptedCardTypes;

    EstablishmentType(int code, String description, Set<CardType> acceptedCardTypes) {
        this.code = code;
        this.description = description;
        this.acceptedCardTypes = acceptedCardTypes;
    }

    public static EstablishmentType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (EstablishmentType t : EstablishmentType.values()) {
            if (cod.equals(t.getCode())) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid Id " + cod);
    }

}
