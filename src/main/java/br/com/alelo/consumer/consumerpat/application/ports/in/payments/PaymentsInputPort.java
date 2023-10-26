package br.com.alelo.consumer.consumerpat.application.ports.in.payments;

import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;

public interface PaymentsInputPort {
    void payment(Payments payments);
}
