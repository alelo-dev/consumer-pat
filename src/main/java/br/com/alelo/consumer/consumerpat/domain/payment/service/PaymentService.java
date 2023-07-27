package br.com.alelo.consumer.consumerpat.domain.payment.service;

import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;

public interface PaymentService {
    void registerPayment(final Payment newPayment);

}
