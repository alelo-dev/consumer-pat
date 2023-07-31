package br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger;

import br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger.entity.LedgerRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LedgerJpaRepository extends JpaRepository<LedgerRecordEntity, UUID> {
}
