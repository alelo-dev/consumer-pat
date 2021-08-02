package br.com.alelo.consumer.consumerpat.service.checkout;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.enums.CardType;

public interface CheckoutStrategy {

    BigDecimal calculateValue(BigDecimal value);

    CardType acceptedCardType();

}
