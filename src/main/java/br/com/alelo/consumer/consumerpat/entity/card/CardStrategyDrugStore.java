package br.com.alelo.consumer.consumerpat.entity.card;

import java.math.BigDecimal;

public class CardStrategyDrugStore implements ICard{

    @Override
    public BigDecimal calculateBalance(BigDecimal value) {

        return value;

   }
}
