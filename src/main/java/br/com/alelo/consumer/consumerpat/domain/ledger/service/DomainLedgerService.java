package br.com.alelo.consumer.consumerpat.domain.ledger.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.ledger.entity.LedgerRecord;
import br.com.alelo.consumer.consumerpat.domain.ledger.repository.LedgerRepository;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;

import java.util.UUID;

import static br.com.alelo.consumer.consumerpat.domain.ledger.entity.EntryOperationType.CREDIT;
import static br.com.alelo.consumer.consumerpat.domain.ledger.entity.EntryOperationType.DEBIT;

public class DomainLedgerService implements LedgerService {

    private final LedgerRepository ledgerRepository;

    public DomainLedgerService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public void credit(final CardBalance cardBalance) {
        var newLedgerRecord = new LedgerRecord(
                UUID.randomUUID(), cardBalance.getCard().getCardNumber(), cardBalance.getAmount(), CREDIT);
        ledgerRepository.save(newLedgerRecord);
    }

    public void debit(final Payment payment) {
        var newLedgerRecord = new LedgerRecord(UUID.randomUUID(), payment.getCardNumber(), payment.getAmount(), DEBIT);
        ledgerRepository.save(newLedgerRecord);
    }
}
