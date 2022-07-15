package br.com.alelo.consumer.consumerpat.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    private BigDecimalUtils() {}

    public static Boolean isGreaterThanZero(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }
}
