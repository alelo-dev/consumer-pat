package br.com.alelo.consumer.consumerpat.payment.application.port.out;

import br.com.alelo.consumer.consumerpat.payment.domain.Payment;

public interface SavePaymentPort {

    Payment save(Payment payment);
}
