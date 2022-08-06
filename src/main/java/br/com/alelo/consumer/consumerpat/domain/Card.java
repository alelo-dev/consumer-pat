package br.com.alelo.consumer.consumerpat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_balance")
    private BigDecimal cardBalance;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardtype;

    private boolean active;

    @ManyToOne
    private Consumer consumer;
}
