package br.com.alelo.consumer.consumerpat.controller.model;

public enum EstablishmentType {
    FOOD(1),
    DRUG_STORE(2),
    FUEL(3);

    private Integer code;

    EstablishmentType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
