package br.com.alelo.consumer.consumerpat.model;

public enum EstablishmentType {
    FOOD(1), DRUGSTORE(2), FUEL(3), UNKNOWN(4);

    private final int id;

    EstablishmentType(int id) {
        this.id = id;
    }

    public static EstablishmentType getById(int id) {
        for (EstablishmentType e : values()) {
            if (e.id == id) return e;
        }
        return UNKNOWN;
    }
}
