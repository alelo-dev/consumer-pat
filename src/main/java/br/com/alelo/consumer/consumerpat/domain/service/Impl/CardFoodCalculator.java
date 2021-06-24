package br.com.alelo.consumer.consumerpat.domain.service.Impl;

import br.com.alelo.consumer.consumerpat.domain.service.CardCalculatorStrategy;

public class CardFoodCalculator implements CardCalculatorStrategy {
    @Override
    public double calculate(double value) {
        double cashback  = (value / 100) * 10;
        return value - cashback;
    }
}
