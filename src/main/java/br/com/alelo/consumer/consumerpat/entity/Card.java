package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "CONSUMER_ID", "CARD_TYPE" }))
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String cardNumber;

    @Column(name = "CARD_TYPE", nullable = false)
    private CardType cardType;

    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;

    public Card(String cardNumber, CardType cardType, BigDecimal balance, Consumer consumer) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.balance = balance;
        this.consumer = consumer;
    }

}
