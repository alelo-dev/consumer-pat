package br.com.alelo.consumer.consumerpat.service.card;

public enum CardTypeEnum {

    Food,
    DrugStore,
    Fuel;

    public int getValue() {
        return ordinal() + 1;
    }
}