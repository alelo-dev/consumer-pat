package br.com.alelo.consumer.consumerpat.enums;

public enum TipoCartao {
    FOOD (1),
    DRUGSTORE(2),
    FUEL(3);

    private int value;

    TipoCartao(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
