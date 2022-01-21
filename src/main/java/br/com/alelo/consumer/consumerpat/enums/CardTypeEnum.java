package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CardTypeEnum {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private Integer value;

    public static CardTypeEnum getEnum(final Integer cardType) {
        return Arrays.stream(values()).filter(enumItem -> enumItem.getValue().equals(cardType)).findFirst().orElse(null);
    }
}
