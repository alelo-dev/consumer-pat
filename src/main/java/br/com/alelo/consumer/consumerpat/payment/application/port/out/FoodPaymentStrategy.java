package br.com.alelo.consumer.consumerpat.payment.application.port.out;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FoodPaymentStrategy implements PaymentStrategy {

    /**
     * Given payment amount, apply 10% discount to final price.
     */
    private static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    @Override
    public boolean canHandle(Card card, CardTypeEnum establishmentType) {
        return establishmentType.isFood() && card.getType().isFood();
    }

    @Override
    public Payment calculate(Payment payment) {

        var totalDiscount = payment.getAmount().multiply(DISCOUNT_RATE);
        var totalAmount = payment.getAmount().subtract(totalDiscount);

        payment.setAmount(totalAmount);

        return payment;
    }
}
