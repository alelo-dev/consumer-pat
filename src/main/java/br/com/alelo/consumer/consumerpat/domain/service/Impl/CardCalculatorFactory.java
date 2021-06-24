package br.com.alelo.consumer.consumerpat.domain.service.Impl;

import br.com.alelo.consumer.consumerpat.domain.service.CardCalculatorStrategy;
import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;

public class CardCalculatorFactory {


    public static CardCalculatorStrategy getCardCalculator(EstablishmentType establishmentType){
        switch (establishmentType){
            case Drugstore:
                return new CardDrugstoreCalculator();
            case Food:
                return new CardFoodCalculator();
            case Fuel:
                return new CardFuelCalculator();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
