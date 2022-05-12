package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.types.CardAndEstablishmentType;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //alterado para String caso exista algum caso que inicie com 0
    @Column(length = 25, nullable = false)
    private String number;

    @Column(length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private CardAndEstablishmentType type;

    @Column(precision = 15, scale = 2, nullable = false)
    private Double balance;

    @Column(nullable = false)
    private boolean discontinued;

}
