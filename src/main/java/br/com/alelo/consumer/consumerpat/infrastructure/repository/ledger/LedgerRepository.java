package br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger;

import br.com.alelo.consumer.consumerpat.domain.ledger.entity.LedgerRecord;
import br.com.alelo.consumer.consumerpat.domain.ledger.repository.DomainLedgerRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger.converter.LedgerConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class LedgerRepository implements DomainLedgerRepository {

    private final LedgerJpaRepository ledgerRepository;
    private final LedgerConverter ledgerConverter;

    @Override
    public void save(LedgerRecord ledgerRecord) {
        var ledgerRecordEntity = ledgerConverter.toEntity(ledgerRecord);
        ledgerRecordEntity.setCreatedAt(LocalDateTime.now());
        ledgerRepository.save(ledgerRecordEntity);
    }
}
