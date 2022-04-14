package br.com.alelo.consumer.consumerpat.type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CardType {
    FOOD(1),
    DRUGSTORE(2),
    FUEL(3);

    int cardType;

    CardType(int type) {
        this.cardType = type;
    }

    @JsonValue
    public int getCardType() {
        return cardType;
    }
}
