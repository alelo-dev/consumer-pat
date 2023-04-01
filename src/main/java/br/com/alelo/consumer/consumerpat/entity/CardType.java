package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;

public enum CardType {
    FOOD(10.0)
            {
                @Override
                public BigDecimal taxCalculate(BigDecimal value) {
                    BigDecimal tax = value.divide(new BigDecimal(100)).multiply(percent);
                    return value.subtract(tax);
                }
            }, MEAL(35.0)
            {
                @Override
                public BigDecimal taxCalculate(BigDecimal value) {
                    BigDecimal tax = value.divide(new BigDecimal(100)).multiply(percent);
                    return value.add(tax);
                }
            }, PHARMACY(0.0)
            {
                @Override
                public BigDecimal taxCalculate(BigDecimal value) {
                    return value;
                }
            };

    protected final BigDecimal percent;

    public abstract BigDecimal taxCalculate(BigDecimal value);

    CardType(double percent) {
        this.percent = BigDecimal.valueOf(percent);
    }
}
