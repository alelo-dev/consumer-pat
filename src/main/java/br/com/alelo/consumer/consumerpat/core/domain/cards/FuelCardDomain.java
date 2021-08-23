package br.com.alelo.consumer.consumerpat.core.domain.cards;

import java.math.BigDecimal;

public class FuelCardDomain implements Card {

    private final BigDecimal FEE = BigDecimal.valueOf(35L);

    @Override
    public BigDecimal calculateBalance(BigDecimal balance, BigDecimal value) {
        BigDecimal valueTax = value.multiply(FEE).divide(BigDecimal.valueOf(100L));

        return balance.subtract(value.add(valueTax));
    }
}
