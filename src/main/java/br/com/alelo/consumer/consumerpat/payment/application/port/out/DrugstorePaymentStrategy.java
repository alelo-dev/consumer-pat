package br.com.alelo.consumer.consumerpat.payment.application.port.out;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class DrugstorePaymentStrategy implements PaymentStrategy {

    @Override
    public boolean canHandle(Card card, CardTypeEnum establishmentType) {
        return establishmentType.isDrugstore() && card.getType().isDrugstore();
    }

    @Override
    public Payment calculate(Payment payment) {
        return payment;
    }
}
