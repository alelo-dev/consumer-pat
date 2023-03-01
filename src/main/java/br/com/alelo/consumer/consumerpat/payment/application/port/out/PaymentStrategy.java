package br.com.alelo.consumer.consumerpat.payment.application.port.out;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;

public interface PaymentStrategy {

    boolean canHandle(Card card, CardTypeEnum establishMentType);

    Payment calculate(Payment payment);
}
