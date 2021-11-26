package br.com.alelo.consumer.consumerpat.enums;

public enum ConsumerEnum {
    FOOD_CARD(1),DRUG_STORE_CARD(2),FUEL_CARD(3);

    private final int value;
    private ConsumerEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
