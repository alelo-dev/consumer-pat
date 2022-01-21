package br.com.alelo.consumer.consumerpat.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer number;
    private Double balance;
    private Integer cardType;
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
