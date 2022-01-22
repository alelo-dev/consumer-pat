package br.com.alelo.consumer.consumerpat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum EstablishmentTypeEnum {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private Integer value;

    public static EstablishmentTypeEnum getEnum(final Integer establishmentType) {
        return Arrays.stream(values()).filter(enumItem -> enumItem.getValue().equals(establishmentType)).findFirst().orElse(null);
    }
}
