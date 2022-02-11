package br.com.alelo.consumer.consumerpat.entity;

/**
 * Enun para tratar os tipos de cartão
 */
public enum CardType {

    FOOD_CARD (0), FUEL_CARD(1), DRUGSTORE_CARD(2);

    private int value;
    
    private  CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
