package br.com.alelo.consumer.consumerpat.controller.model;

public enum EstablishmentType {
    ALIMENTACAO(1),
    FARMACIA(2),
    POSTO_COMBUSTIVEL(3);

    private Integer code;

    EstablishmentType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
