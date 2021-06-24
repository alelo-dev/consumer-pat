package br.com.alelo.consumer.consumerpat.domain.service.Impl;

import br.com.alelo.consumer.consumerpat.domain.service.CardCalculatorStrategy;

public class CardDrugstoreCalculator implements CardCalculatorStrategy {
    @Override
    public double calculate(double value) {
        return value;
    }
}
