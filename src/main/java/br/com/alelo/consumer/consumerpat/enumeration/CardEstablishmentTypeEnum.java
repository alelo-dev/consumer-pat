package br.com.alelo.consumer.consumerpat.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum CardEstablishmentTypeEnum {
    FOOD(1),
    DRUG_SOTRE(2),
    FUEL(3);

    Integer typeNumber;

    CardEstablishmentTypeEnum(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }

    public Integer getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }

    public static Optional<CardEstablishmentTypeEnum> fromValue(int codigo) {
        return Arrays.stream(CardEstablishmentTypeEnum.values()).filter(type -> type.typeNumber.equals(codigo)).findFirst();
    }


}
