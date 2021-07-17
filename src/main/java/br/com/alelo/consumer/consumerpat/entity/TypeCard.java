package br.com.alelo.consumer.consumerpat.entity;

public enum TypeCard implements CardDiscountRule {
    FOOD_CARD,
    FUEL_CARD,
    DRUG_STORE
}

interface CardDiscountRule {
    default Double computeDebit(Double debitAmount, Double percentDiscount){
        return debitAmount * (1 - percentDiscount);
    };
}
