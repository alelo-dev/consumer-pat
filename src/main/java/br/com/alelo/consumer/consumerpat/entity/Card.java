package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Card {

    //cards
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    Integer id;

    int foodCardNumber;
    double foodCardBalance;

    int fuelCardNumber;
    double fuelCardBalance;

    int drugstoreCardNumber;
    double drugstoreCardBalance;

//    Integer consumerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    @JsonBackReference
    private Consumer consumer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return  this.foodCardNumber == card.foodCardNumber
                && Double.compare(card.foodCardBalance, this.foodCardBalance) == 0
                && this.fuelCardNumber == card.fuelCardNumber
                && Double.compare(card.fuelCardBalance, this.fuelCardBalance) == 0
                && this.drugstoreCardNumber == card.drugstoreCardNumber
                && Double.compare(card.drugstoreCardBalance, this.drugstoreCardBalance) == 0
                && Objects.equals(this.id, card.id);
    }
}
