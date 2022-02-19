package br.com.alelo.consumer.consumerpat.entity.card;

import java.math.BigDecimal;

public class CardStrategyCombustivel implements ICard {
    @Override
    public BigDecimal calculateBalance(BigDecimal value) {

        BigDecimal tax  =  value.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(35)).setScale(2);
        value = value.add(tax);
        return value;
    }
}
