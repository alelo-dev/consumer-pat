package br.com.alelo.consumer.consumerpat.domain.enumeration;

/**
 * Enumeração contendo os tipos de cartões de benefícios
 * existentes na aplicaçào.
 */
public enum CardType {

    FOOD_CARD("Food Card"),
    FUEL_CARD("Fuel Card"),
    DRUGSTORE_CARD("Drugstore Card");

    private final String type;

    private CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static boolean exists(CardType cardType) {

        boolean exists = false;

        for (CardType e : CardType.values()) {
            if (e.equals(cardType)) {
                return true;
            }
        }

        return exists;
    }

    public static boolean notExists(CardType cardType) {
        return !exists(cardType);
    }

}
