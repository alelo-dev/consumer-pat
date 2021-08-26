package br.com.alelo.consumer.consumerpat.entity;

public enum CardType {
    FUEL("FUEL"),DRUG("DRUG"),FOOD("FOOD");

    public String cardType;
    CardType(String cardType) {
        cardType = cardType;
    }
}
