package br.com.alelo.consumer.consumerpat.payment.application.port.out;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FuelPaymentStrategy implements PaymentStrategy {

    /**
     * Given payment amount, apply 35% tax to final price.
     */
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.35);

    @Override
    public boolean canHandle(Card card, CardTypeEnum establishmentType) {
        return establishmentType.isFuel() && card.getType().isFuel();
    }

    @Override
    public Payment calculate(Payment payment) {

        var totalTaxes = payment.getAmount().multiply(TAX_RATE);
        var totalAmount = payment.getAmount().add(totalTaxes);

        payment.setAmount(totalAmount);

        return payment;
    }
}
