package br.com.alelo.consumer.consumerpat.data.model;

import br.com.alelo.consumer.consumerpat.util.TypeCardsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CARDS")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "CARD_NUMBER", nullable = false, length = 16)
    Long number;

    @Column(name = "BALANCE", nullable = false)
    BigDecimal balance;

    @Column(name = "TYPE_CARD", nullable = false)
    TypeCardsEnum type;

    @ManyToOne
    Consumer consumer;

    public Card(Consumer consumer, TypeCardsEnum type, BigDecimal balance, Long number) {
        this.balance = balance;
        this.consumer = consumer;
        this.number = number;
        this.type = type;
    }
}
