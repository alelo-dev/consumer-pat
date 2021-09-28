package br.com.alelo.consumer.consumerpat.domain.entity.enums;

import lombok.Getter;

@Getter
public enum CardType {

    FOOD(1),
    DRUGSTORE(2),
    FUEL(3),
    ;

    int cardId;

    CardType(int cardId){
        this.cardId = cardId;
    }
}
