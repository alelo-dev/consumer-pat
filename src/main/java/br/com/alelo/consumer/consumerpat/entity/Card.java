package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.objectvalue.CardType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Data
@Entity
public class Card {

    //cards
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    Integer id;

    @Positive(message = "Número do cartão deve ser maior que zero")
    int number;

    @PositiveOrZero(message = "Saldo insuficiente ou menor que zero")
    double balance;

    CardType cardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    @JsonBackReference
    private Consumer consumer;

    public void addBalance(double value){
        this.setBalance(this.getBalance() + value);
    }

    public void buyingTransaction(double value){
        this.setBalance(this.getBalance() - this.getCardType().calculateTransactionValue(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return  this.number == card.number
                && Double.compare(card.balance, this.balance) == 0
                && this.getCardType() == card.getCardType()
                && Objects.equals(this.id, card.id);
    }
}
