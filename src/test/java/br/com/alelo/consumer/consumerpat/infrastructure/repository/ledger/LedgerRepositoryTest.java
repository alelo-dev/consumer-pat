package br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.ledger.entity.EntryOperationType;
import br.com.alelo.consumer.consumerpat.domain.ledger.entity.LedgerRecord;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger.converter.LedgerConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger.entity.LedgerRecordEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LedgerRepositoryTest {

    @InjectMocks
    private LedgerRepository ledgerRepository;

    @Mock
    private LedgerJpaRepository ledgerJpaRepository;

    @Mock
    private LedgerConverter ledgerConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveLedgerRecord() {
        CardNumber cardNumber = new CardNumber("1234567890123456");
        BigDecimal amount = BigDecimal.valueOf(100.0);
        EntryOperationType entryOperationType = EntryOperationType.CREDIT;

        LedgerRecord ledgerRecord = new LedgerRecord(cardNumber, amount, entryOperationType);
        LedgerRecordEntity ledgerRecordEntity = LedgerRecordEntity.builder()
                .id(UUID.randomUUID())
                .cardNumber(cardNumber.getCardNumber())
                .amount(amount)
                .entryOperationType(entryOperationType)
                .entryOperationDate(ledgerRecord.getEntryOperationDate())
                .createdAt(LocalDateTime.now())
                .build();

        when(ledgerConverter.toEntity(any(LedgerRecord.class))).thenReturn(ledgerRecordEntity);

        ledgerRepository.save(ledgerRecord);

        verify(ledgerConverter).toEntity(ledgerRecord);
        verify(ledgerJpaRepository).save(ledgerRecordEntity);
    }
}
