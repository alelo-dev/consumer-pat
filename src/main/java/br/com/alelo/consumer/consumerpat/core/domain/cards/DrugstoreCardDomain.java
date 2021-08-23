package br.com.alelo.consumer.consumerpat.core.domain.cards;

import java.math.BigDecimal;

public class DrugstoreCardDomain implements Card {

    @Override
    public BigDecimal calculateBalance(BigDecimal balance, BigDecimal value) {
        return balance.subtract(value);
    }
}
