package br.com.alelo.consumer.consumerpat.service.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.InsufficientFundsException;
import br.com.alelo.consumer.consumerpat.exception.InvalidCardTypeException;

public class CheckoutContext {

    private CheckoutStrategy checkoutStrategy;

    public CheckoutContext(CheckoutStrategy checkoutStrategy) {
        this.checkoutStrategy = checkoutStrategy;
    }

    public void setCheckoutStrategy(CheckoutStrategy checkoutStrategy) {
        this.checkoutStrategy = checkoutStrategy;
    }

    public BigDecimal validateAndCalculate(Card card, BigDecimal value) {
        if (card.getCardType() != checkoutStrategy.acceptedCardType()) {
            throw new InvalidCardTypeException();
        }

        BigDecimal finalValue = checkoutStrategy.calculateValue(value).setScale(2, RoundingMode.HALF_EVEN);

        // TODO: considerar saldo com ou sem cashback?
        // finalValue > card balance
        if (finalValue.compareTo(card.getBalance()) > 0) {
            throw new InsufficientFundsException();
        }

        return finalValue;
    }

}
