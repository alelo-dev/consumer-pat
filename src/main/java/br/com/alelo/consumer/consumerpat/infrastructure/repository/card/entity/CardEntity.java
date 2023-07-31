package br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardType;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.entity.ConsumerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class CardEntity {

    @Id
    @Column(name = "card_number")
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private ConsumerEntity consumer;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "card_balance_id", referencedColumnName = "id")
    private CardBalanceEntity cardBalance;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

