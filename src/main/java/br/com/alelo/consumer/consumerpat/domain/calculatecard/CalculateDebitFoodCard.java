package br.com.alelo.consumer.consumerpat.domain.calculatecard;

public class CalculateDebitFoodCard implements CalculateCard {

    @Override
    public double calculate(double value) {
        double cashback = (value / 100) * 10;
        value = value - cashback;

        return value;
    }
}
