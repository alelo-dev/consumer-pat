package br.com.alelo.consumer.consumerpat.models.enums;

public enum EstablishmentEnum {

    FOOD("Alimentação"),
    DRUGSTORE("Farmácia"),
    FUEL("Posto de combustível");

    private final String description;

    EstablishmentEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
