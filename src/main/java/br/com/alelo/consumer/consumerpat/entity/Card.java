package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Card {

    @Id
    private String idCard;
    
    private Long cardNumber;
    
    private BigDecimal cardBalance;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(referencedColumnName="idTypeCard", name=" ID_TYPE_CARD_CONSUMER", nullable = false)
    private TypeCard typeCard;
}
