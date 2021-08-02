package br.com.alelo.consumer.consumerpat.service.checkout;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.enums.CardType;

public class DrugstoreCheckout implements CheckoutStrategy {

    private static final BigDecimal TAX_VALUE = new BigDecimal("1.35");

    @Override
    public BigDecimal calculateValue(BigDecimal value) {
        return value.multiply(TAX_VALUE);
    }

    @Override
    public CardType acceptedCardType() {
        return CardType.DRUGSTORE;
    }

}
