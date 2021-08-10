package br.com.alelo.consumer.consumerpat.strategy.card;

public class FuelCard implements RuleCard {
    @Override
    public Double calcular(Double valueTicket) {
        Double tax  = valueTicket * 0.35;
        return valueTicket + tax;
    }
}
