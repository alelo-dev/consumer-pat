package br.com.alelo.consumer.consumerpat.entity.enums;

public enum EstablishmentType {

    FOOD(1), DRUGSTORE(2), FUEL(3);

    private int value;

    EstablishmentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }           
    
}
