package br.com.alelo.consumer.consumerpat.strategy.card;

public class DrugstoreCard implements RuleCard {

    @Override
    public Double calcular(Double valueTicket) {
        return valueTicket;
    }
}
