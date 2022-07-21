package br.com.alelo.consumer.consumerpat.domain.converter;

import java.math.BigDecimal;

public final class BigDecimalConverter {

    private BigDecimalConverter() {
        super();
    }

    public static BigDecimal convertToBigDecimal(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    public static BigDecimal convertToBigDecimal(Double number) {

        if (number == null) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(number);
    }

}
