package br.com.alelo.consumer.consumerpat.entity.enumeration;

import java.math.BigDecimal;

public enum CardType {
    FOOD(-10.0),
    DRUGSTORE(0.0),
    FUEL(35.0);

    private final double adjustment;

    CardType(double adjustment) {
        this.adjustment = adjustment;
    }

    public BigDecimal getAdjustment() {
        if (adjustment == 0) {
            return BigDecimal.ONE;
        }
        return BigDecimal.valueOf((100 + adjustment) / 100);
    }
}
