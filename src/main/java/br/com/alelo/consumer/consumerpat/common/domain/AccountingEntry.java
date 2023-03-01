package br.com.alelo.consumer.consumerpat.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class AccountingEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountingEntryId;

    @ManyToOne
    private Card card;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private AccountingEntryType type;

    private LocalDate date;
}
