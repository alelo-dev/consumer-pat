package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.domain.enums.CardTypeEnum;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardsId;

    @Column(nullable = false)
    private int foodCardNumber;

    @Column(nullable = false)
    private double foodCardBalance;

    @Column(nullable = false)
    private int fuelCardNumber;

    @Column(nullable = false)
    private double fuelCardBalance;

    @Column(nullable = false)
    private int drugstoreNumber;

    @Column(nullable = false)
    private double drugstoreCardBalance;

    @Column(nullable = false)
    private Integer cardNumber;

    @Column(nullable = false)
    private int cardType;

}
