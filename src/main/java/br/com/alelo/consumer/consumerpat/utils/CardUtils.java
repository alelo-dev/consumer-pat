package br.com.alelo.consumer.consumerpat.utils;

import org.springframework.stereotype.Component;

@Component
public class CardUtils {

    //Para compras com cartão combustível, acréscimo de 35%
    public double calculateFuelCardValue(double value) {
        Double tax  = (value / 100) * 35;
        return value + tax;
    }

    //Para compras com cartão alimentação, cashback de 10%
    public double calculateFoodCardValue(double value) {
        Double cashback  = (value / 100) * 10;
        return value - cashback;
    }
}
