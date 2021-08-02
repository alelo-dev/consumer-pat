package br.com.alelo.consumer.consumerpat.service.checkout;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.enums.CardType;

public class FuelCheckout implements CheckoutStrategy {

    @Override
    public BigDecimal calculateValue(BigDecimal value) {
        return value;
    }

    @Override
    public CardType acceptedCardType() {
        return CardType.FUEL;
    }

}
