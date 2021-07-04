package br.com.alelo.consumer.consumerpat.domain.card;

import br.com.alelo.consumer.consumerpat.domain.consumer.Consumer;
import br.com.alelo.consumer.consumerpat.infra.handler.exception.badRequest.BalanceBadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "card_id")
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "card_type")
    private CardType type;
    @Column(name = "number_card")
    private String numberCard;

    @Column(name = "balance")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public Card(CardType type, String numberCard, double balance, long consumerId) {
        this.type = type;
        this.numberCard = numberCard;
        this.balance = balance;
        this.consumer = Consumer.builder().id(consumerId).build();
    }

    public void updateBalance(double balance) {
        final double newBalance = this.balance + balance;
        if (newBalance < 0 ){
            throw new BalanceBadRequestException("O valor da compra é superior ao saldo: " + this.balance);
        }
        this.balance = newBalance;
    }
}
