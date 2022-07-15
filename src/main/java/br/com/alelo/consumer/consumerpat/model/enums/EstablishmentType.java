package br.com.alelo.consumer.consumerpat.model.enums;

import java.math.BigDecimal;

public enum EstablishmentType {

    FOOD(1, BigDecimal.valueOf(0.9)),
    DRUGSTORE(2, BigDecimal.valueOf(1.0)),
    FUEL(3, BigDecimal.valueOf(1.35));

    private Integer value;
    private BigDecimal multiplyFactor;

    EstablishmentType(Integer value, BigDecimal multiplyFactor) {
        this.value = value;
        this.multiplyFactor = multiplyFactor;
    }

    public Integer getValue() {
        return value;
    }

    public BigDecimal getMultiplyFactor() {
        return multiplyFactor;
    }

    public static EstablishmentType fromCode(Integer value) {
        for (EstablishmentType t : EstablishmentType.values()) {
            if (t.getValue().equals(value))
                return t;
        }
        throw new IllegalArgumentException("Establishment Type Not Found!");
    }
}
