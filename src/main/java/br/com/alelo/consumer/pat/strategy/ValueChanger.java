package br.com.alelo.consumer.pat.strategy;

import java.math.BigDecimal;

@FunctionalInterface
public interface ValueChanger {

    BigDecimal change(BigDecimal value);

    default BigDecimal percentage(BigDecimal value, BigDecimal amount) {
        return value.divide(BigDecimal.valueOf(100)).multiply(amount);
    }

}
