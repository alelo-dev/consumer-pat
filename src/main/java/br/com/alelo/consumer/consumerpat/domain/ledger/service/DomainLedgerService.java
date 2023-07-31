package br.com.alelo.consumer.consumerpat.domain.ledger.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.ledger.entity.LedgerRecord;
import br.com.alelo.consumer.consumerpat.domain.ledger.repository.DomainLedgerRepository;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static br.com.alelo.consumer.consumerpat.domain.ledger.entity.EntryOperationType.CREDIT;
import static br.com.alelo.consumer.consumerpat.domain.ledger.entity.EntryOperationType.DEBIT;

@Service
@RequiredArgsConstructor
public class DomainLedgerService implements LedgerService {

    private final DomainLedgerRepository ledgerRepository;

    public void credit(final Card card) {
        var newLedgerRecord = new LedgerRecord(card.getCardNumber(), card.getCardBalance().getAmount(), CREDIT);
        ledgerRepository.save(newLedgerRecord);
    }

    public void debit(final Payment payment) {
        var newLedgerRecord = new LedgerRecord(payment.getCardNumber(), payment.getAmount(), DEBIT);
        ledgerRepository.save(newLedgerRecord);
    }
}
