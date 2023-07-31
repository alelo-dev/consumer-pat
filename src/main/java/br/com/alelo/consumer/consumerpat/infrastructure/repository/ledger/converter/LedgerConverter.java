package br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger.converter;

import br.com.alelo.consumer.consumerpat.domain.ledger.entity.LedgerRecord;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger.entity.LedgerRecordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LedgerConverter {

    public LedgerRecordEntity toEntity(final LedgerRecord ledgerRecord) {
        return LedgerRecordEntity.builder()
                .cardNumber(ledgerRecord.getCardNumber().getCardNumber())
                .amount(ledgerRecord.getAmount())
                .entryOperationType(ledgerRecord.getEntryOperationType())
                .entryOperationDate(ledgerRecord.getEntryOperationDate())
                .build();
    }
}
