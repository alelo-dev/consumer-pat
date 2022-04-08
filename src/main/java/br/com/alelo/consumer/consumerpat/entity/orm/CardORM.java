package br.com.alelo.consumer.consumerpat.entity.orm;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "card")
public class CardORM implements Card {

    @Id
    private String number;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "type", nullable = false)
    private CardType type;

}
