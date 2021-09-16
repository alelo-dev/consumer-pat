package br.com.alelo.consumer.consumerpat.domain;

public enum Industry {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    public final int code;

    Industry(int code) {
        this.code = code;
    }
}