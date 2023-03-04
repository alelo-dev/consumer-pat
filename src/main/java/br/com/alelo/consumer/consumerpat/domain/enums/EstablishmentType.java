package br.com.alelo.consumer.consumerpat.domain.enums;

public enum EstablishmentType {
    FOOD_CARD(1),
    DRUG_STORE(2),
    FUEL_CARD(3);

    private final int value;

    EstablishmentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EstablishmentType getEnum(int value) {
        for (EstablishmentType e : values()) {
            if (e.getValue() == value)
                return e;
        }
        throw new IllegalArgumentException();
    }
}
