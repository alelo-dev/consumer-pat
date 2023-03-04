package br.com.alelo.consumer.consumerpat.domain.calculatecard;

import br.com.alelo.consumer.consumerpat.domain.enums.EstablishmentType;

public class CalculateDebitCardFactory implements CalculateCardFactory {

    public CalculateCard createCalculate(EstablishmentType establishmentType) {

        if (EstablishmentType.FOOD_CARD == establishmentType) {
            return new CalculateDebitFoodCard();
        } else if (EstablishmentType.DRUG_STORE ==  establishmentType) {
            return new CalculateDebitDrugstoreCard();
        } else {
            return new CalculateDebitFuelCard();
        }
    }
}
