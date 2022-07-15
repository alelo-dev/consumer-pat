package br.com.alelo.consumer.consumerpat.model.enums;

public enum EstablishmentType {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private Integer value;

    EstablishmentType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static EstablishmentType fromCode(Integer value) {
        for (EstablishmentType t : EstablishmentType.values()) {
            if (t.getValue().equals(value))
                return t;
        }
        throw new IllegalArgumentException("Establishment Type Not Found!");
    }
}
