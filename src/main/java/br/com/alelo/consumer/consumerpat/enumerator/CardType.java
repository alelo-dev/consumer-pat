package br.com.alelo.consumer.consumerpat.enumerator;

public enum CardType {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    private Integer value;

    CardType(Integer value) {
        this.value = value;
    }

    public static CardType fromValue(String text) {
        for (CardType b : CardType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

}
