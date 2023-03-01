package br.com.alelo.consumer.consumerpat.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CardTypeEnum {

    FOOD(1, "Food"),
    DRUGSTORE(2, "Drugstore"),
    FUEL(3, "Fuel"),
    UNKNOWN(4, "Unknown");

    private final int value;
    private final String name;

    public static CardTypeEnum of(int value) {
        return Arrays.stream(values())
                .filter(cardType -> cardType.getValue() == value)
                .findFirst()
                .orElse(UNKNOWN);
    }

    public boolean isFood() {
        return this.equals(FOOD);
    }

    public boolean isDrugstore() {
        return this.equals(DRUGSTORE);
    }

    public boolean isFuel() {
        return this.equals(FUEL);
    }
}
