package br.com.alelo.consumer.consumerpat.domain.ledger.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;

public interface LedgerService {
    void credit(final Card card);

    void debit(final Payment payment);
}
