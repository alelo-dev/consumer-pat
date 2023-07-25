package br.com.alelo.consumer.consumerpat.domain.ledger.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;

public interface LedgerService {
    void credit(final CardBalance cardBalance);

    void debit(final Payment payment);
}
