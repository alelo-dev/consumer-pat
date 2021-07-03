package br.com.alelo.consumer.consumerpat.domain.entity;

import br.com.alelo.consumer.consumerpat.domain.service.CardContext;
import br.com.alelo.consumer.consumerpat.domain.service.impl.DrugStoreCard;
import br.com.alelo.consumer.consumerpat.domain.service.impl.FoodCard;
import br.com.alelo.consumer.consumerpat.domain.service.impl.FuelCard;


public enum CardType {

    FOOD(1, "FOOD", CardContext.builder().strategy(FoodCard.builder().build()).build()),
    DRUGSTORE(2, "DRUGSTORE", CardContext.builder().strategy(DrugStoreCard.builder().build()).build()),
    FUEL(3, "FUEL", CardContext.builder().strategy(FuelCard.builder().build()).build());

    private final int code;
    private final String type;
    private final CardContext cardContext;

    CardType(int code, String type, CardContext cardContext) {
        this.code = code;
        this.type = type;
        this.cardContext = cardContext;
    }

    public CardContext getCardContext() {
        return cardContext;
    }
}
