package br.com.alelo.consumer.consumerpat.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Card {

    @Id
    @Column(name = "number")
    int number;

    double cardBalance;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    Consumer consumer;

    @Column(name = "card_type")
    int cardType;
}
