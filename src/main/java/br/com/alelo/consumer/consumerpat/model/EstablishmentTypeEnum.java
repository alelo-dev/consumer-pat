package br.com.alelo.consumer.consumerpat.model;

public enum EstablishmentTypeEnum {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private Integer id;

    EstablishmentTypeEnum(Integer id) {
        this.id = id;
    }

    public Integer getDescription() {
        return id;
    }
}
