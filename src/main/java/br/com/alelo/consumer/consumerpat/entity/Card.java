package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "BALANCE")
    private Double balance;
    @Column(name = "CARD_TYPE")
    private Integer cardType;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card consumer = (Card) o;
        return number == consumer.number
                && Double.compare(consumer.balance, balance) == 0;
    }
}
