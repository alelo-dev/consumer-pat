package br.com.alelo.consumer.consumerpat.domain.payment.repository;

import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;

public interface DomainPaymentRepository {
    void save(final Payment payment);
}
