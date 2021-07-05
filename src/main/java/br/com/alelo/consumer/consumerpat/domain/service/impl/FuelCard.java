package br.com.alelo.consumer.consumerpat.domain.service.impl;

import br.com.alelo.consumer.consumerpat.domain.service.Strategy;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Builder
public class FuelCard implements Strategy {

    @Override
    public BigDecimal creditValue(BigDecimal balance, BigDecimal value) {
        return balance.add(value);
    }

    @Override
    public BigDecimal applyCashback(BigDecimal balance, BigDecimal value) {
        BigDecimal cashback  = value.multiply(new BigDecimal("0.35"));
        return balance.subtract((value.subtract(cashback)));
    }

}
