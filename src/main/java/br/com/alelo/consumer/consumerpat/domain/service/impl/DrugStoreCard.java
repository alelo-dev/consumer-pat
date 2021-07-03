package br.com.alelo.consumer.consumerpat.domain.service.impl;

import br.com.alelo.consumer.consumerpat.domain.service.Strategy;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class DrugStoreCard implements Strategy {

    @Override
    public Double creditValue(Double balance, Double value) {
        return balance + value;
    }

    @Override
    public Double applyCashback(Double balance, Double value) {
        Double cashback  = value * 0.1;
        return balance - (value - cashback);
    }

}
