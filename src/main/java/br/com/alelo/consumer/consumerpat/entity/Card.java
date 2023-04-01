package br.com.alelo.consumer.consumerpat.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Setter
@Getter
@RequiredArgsConstructor
@ToString
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private BigDecimal balance;
    private String number;
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public void add(BigDecimal value) {
        value.add(balance);
    }

    public void subtract(BigDecimal value) {
        var tax = cardType.taxCalculate(value);
        tax.subtract(balance);
    }

}
