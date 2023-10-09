package br.com.alelo.consumer.consumerpat.domain.enums;

public enum CardTypeEnum {

    FOOD_CARD(1),
    DRUGSTORE_CARD(2),
    FUEL_CARD(3);

    private int cardType;

    public int getCardType(){
        return cardType;
    }

     CardTypeEnum(int cardType){
        this.cardType = cardType;
    }
}
