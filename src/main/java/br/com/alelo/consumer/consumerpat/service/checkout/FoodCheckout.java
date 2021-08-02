package br.com.alelo.consumer.consumerpat.service.checkout;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.enums.CardType;

public class FoodCheckout implements CheckoutStrategy {

    private static final BigDecimal CASHBACK_VALUE = new BigDecimal("0.9");

    @Override
    public BigDecimal calculateValue(BigDecimal value) {
        return value.multiply(CASHBACK_VALUE);
    }

    @Override
    public CardType acceptedCardType() {
        return CardType.FOOD;
    }

}
