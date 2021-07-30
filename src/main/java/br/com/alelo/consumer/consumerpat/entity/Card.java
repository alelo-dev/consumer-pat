package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enumerated.CardType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "card",
        indexes = {
                @Index(name = "card_idx_01", columnList = "consumer_id"),
                @Index(name = "card_idx_02", columnList = "number")
        })
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    @SequenceGenerator(name = "card_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String number;

    private BigDecimal balance;

    @ElementCollection
    @CollectionTable(name = "card_type",
            joinColumns = @JoinColumn(
                    name = "card_id", nullable = false,
                    foreignKey = @ForeignKey(name = "card_type_fk_01")),
            indexes = @Index(name = "card_type_idx_01", columnList = "card_id"))
    @Column(name = "type", nullable = false)
    private Set<CardType> types;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumer_id", nullable = false,
            foreignKey = @ForeignKey(name = "card_fk_01"))
    private Consumer consumer;
}
