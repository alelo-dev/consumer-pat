package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.entity.enums.TypeCard;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cardNumber;
    private Double cardBalance;
    private TypeCard typeCard;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;
}
