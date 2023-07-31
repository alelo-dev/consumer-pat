package br.com.alelo.consumer.consumerpat.infrastructure.repository.ledger.entity;

import br.com.alelo.consumer.consumerpat.domain.ledger.entity.EntryOperationType;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ledger_record")
public class LedgerRecordEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "card_number")
    private String cardNumber;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_operation_type")
    private EntryOperationType entryOperationType;

    @Column(name = "entry_operation_date")
    private LocalDateTime entryOperationDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

