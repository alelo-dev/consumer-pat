package br.com.alelo.consumer.consumerpat.core.enumeration;

public enum EstablishmentType {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private Integer code;

    EstablishmentType(Integer code) {
        this.code = code;
    }
}
