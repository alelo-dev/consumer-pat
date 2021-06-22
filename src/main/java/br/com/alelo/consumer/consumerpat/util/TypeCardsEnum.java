package br.com.alelo.consumer.consumerpat.util;

import java.math.BigDecimal;

public enum TypeCardsEnum {
    FOOD(1, -0.10), DRUGSTORE(2, 0), FUEL(3, 0.35);

    TypeCardsEnum(Integer value, double tax) {

        this.value = value;

        //Discount or extra cost on purchase
        this.tax = tax;
    }
    private Integer value;
    private double tax;

    public static double getTaxByValue(Integer establishmentType) {
        for (var type: TypeCardsEnum.values()) {
            if (type.getValue() == establishmentType)
                return type.tax;
        }
        throw new IllegalArgumentException("Invalid Type of Establishment: " + establishmentType + ", try with 1 for Food, " +
                "2 for Drugstore or 3 for Fuel.");
    }

    public Integer getValue() {
        return value;
    }
}
