package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.util.function.Function;

public enum CardType {
    FUEL("FUEL",(v) -> {return v.add(v.multiply(new BigDecimal("0.35")).setScale(2));}),
    DRUG("DRUG"),
    FOOD("FOOD",(v) -> {return v.subtract(v.multiply(new BigDecimal("0.1")).setScale(2));});

    public final Function<BigDecimal, BigDecimal> discountAndTax;
    public String cardType;

    CardType(String cardType, Function<BigDecimal,BigDecimal> discountAndTax) {
        this.cardType = cardType;
        this.discountAndTax = discountAndTax;
    }

    CardType(String cardType) {
        this.cardType = cardType;
        this.discountAndTax = v -> {return v;};
    }
}
