package br.com.alelo.consumerpat.core.dataprovider.entity;

import br.com.alelo.consumerpat.core.enumeration.CardType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "card")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(length = 16, nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(length = 9, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CardType type;

    @ManyToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "consumer_id")
    private ConsumerEntity consumer;

    @OneToMany(mappedBy = "card")
    private Set<ExtractEntity> extracts;
}
