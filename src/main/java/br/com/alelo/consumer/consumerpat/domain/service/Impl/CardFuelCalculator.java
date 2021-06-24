package br.com.alelo.consumer.consumerpat.domain.service.Impl;

import br.com.alelo.consumer.consumerpat.domain.service.CardCalculatorStrategy;

public class CardFuelCalculator implements CardCalculatorStrategy {
    @Override
    public double calculate(double value) {
        double tax  = (value / 100) * 35;
        return value+tax;
    }
}
