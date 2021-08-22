package br.com.alelo.consumerpat.core.domain.cards;

import java.math.BigDecimal;

public class FoodCardDomain implements Card {

    private final BigDecimal CASHBACK = BigDecimal.valueOf(10);

    @Override
    public BigDecimal calculateBalance(BigDecimal balance, BigDecimal value) {
        BigDecimal valueTax = value.multiply(CASHBACK).divide(BigDecimal.valueOf(100L));

        return balance.subtract(value.subtract(valueTax));
    }
}
