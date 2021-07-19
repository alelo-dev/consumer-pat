package br.com.alelo.consumer.consumerpat.entity;


import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cardNumber;
    private BigDecimal cardBalance;
    private CardType typeCard;
}
