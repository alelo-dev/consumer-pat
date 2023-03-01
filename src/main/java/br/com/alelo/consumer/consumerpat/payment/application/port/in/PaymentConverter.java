package br.com.alelo.consumer.consumerpat.payment.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.payment.domain.Establishment;
import br.com.alelo.consumer.consumerpat.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {

    public Payment toEntity(Card card, NewPayment newPayment) {

        var establishment = new Establishment();
        establishment.setEstablishmentId(newPayment.getEstablishmentTypeId());
        establishment.setName(newPayment.getEstablishmentName());

        var payment = new Payment();

        payment.setEstablishment(establishment);
        payment.setDescription(newPayment.getDescription());
        payment.setAmount(newPayment.getAmount());
        payment.setDate(newPayment.getDate());
        payment.setCard(card);

        return payment;
    }
}
