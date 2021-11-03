package br.com.alelo.consumer.consumerpat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    CardType cardType; // 1 - food, 2 - fuel, 3 - drugstore
    @Column(unique = true)
    String cardNumber;
    Double cardBalance;
}
