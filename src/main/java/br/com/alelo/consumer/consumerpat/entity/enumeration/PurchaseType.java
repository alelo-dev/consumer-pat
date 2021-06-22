package br.com.alelo.consumer.consumerpat.entity.enumeration;

import java.math.BigDecimal;

public enum PurchaseType {
    FOOD(-10.0),
    DRUGSTORE(0.0),
    FUEL(35.0);

    private final double percentageIncrease;

    PurchaseType(double percentageIncrease) {
        this.percentageIncrease = percentageIncrease;
    }

    public BigDecimal getPercentageAdjustement() {
        return percentageIncrease == 0 ? BigDecimal.ONE : BigDecimal.valueOf((100 + percentageIncrease) / 100);
    }
}
