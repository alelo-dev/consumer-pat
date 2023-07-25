package br.com.alelo.consumer.consumerpat.domain.ledger.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class LedgerRecord {

    private UUID id;
    private CardNumber cardNumber;
    private BigDecimal amount;
    private EntryOperationType entryOperationType;
    private LocalDateTime entryOperationDate;

    public LedgerRecord(UUID id, CardNumber cardNumber, BigDecimal amount, EntryOperationType entryOperationType) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.entryOperationType = entryOperationType;
        this.entryOperationDate = LocalDateTime.now();
    }
}
