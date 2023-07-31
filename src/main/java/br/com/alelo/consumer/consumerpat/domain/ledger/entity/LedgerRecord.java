package br.com.alelo.consumer.consumerpat.domain.ledger.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
public class LedgerRecord {

    private CardNumber cardNumber;
    private BigDecimal amount;
    private EntryOperationType entryOperationType;
    private LocalDateTime entryOperationDate;

    public LedgerRecord(CardNumber cardNumber, BigDecimal amount, EntryOperationType entryOperationType) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.entryOperationType = entryOperationType;
        this.entryOperationDate = LocalDateTime.now();
    }
}
