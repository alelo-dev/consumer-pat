package br.com.alelo.consumer.consumerpat.entity.card;

import java.math.BigDecimal;

public class CardStrategyAlimentacao implements ICard{

    @Override
    public BigDecimal calculateBalance(BigDecimal value) {

            BigDecimal cashback  =  value.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(10)).setScale(2);
            value = value.subtract(cashback);
            return value;
    }
}
