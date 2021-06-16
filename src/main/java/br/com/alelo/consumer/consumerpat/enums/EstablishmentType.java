package br.com.alelo.consumer.consumerpat.enums;

public enum EstablishmentType {
    Food(1),
    Drugstore(2),
    Fuel(3);

    public int value;

    EstablishmentType(int value) {
        this.value = value;
    }
}
