package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

@Data
@Entity
@Builder
@Table(name= "CARD")
public class Card {

    @Id
    @Column(name = "ID_CARD")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CARD_TYPE")
    private CardType cardType;

    @Column(name = "CARD_NUMBER")
    private int cardNumber;

    @Column(name = "CARD_BALANCE")
    private double cardBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ID_CONSUMER")
    private Consumer consumer;

}
