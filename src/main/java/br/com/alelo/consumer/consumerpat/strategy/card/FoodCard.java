package br.com.alelo.consumer.consumerpat.strategy.card;

public class FoodCard implements RuleCard {
    @Override
    public Double calcular(Double valueTicket) {
        Double cashback = valueTicket * 0.1;
        return valueTicket - cashback;
    }
}
