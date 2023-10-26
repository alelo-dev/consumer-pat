package br.com.alelo.consumer.consumerpat.application.ports.out.payments;

import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;

public interface PaymentsOutputPort {
    void payment(Payments payments);
}
