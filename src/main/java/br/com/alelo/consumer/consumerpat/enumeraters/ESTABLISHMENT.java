package br.com.alelo.consumer.consumerpat.enumeraters;

public enum ESTABLISHMENT {

    FOOD(1), DRUGSTORE(2), FUEL(3);

    private final int value;

    ESTABLISHMENT(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
