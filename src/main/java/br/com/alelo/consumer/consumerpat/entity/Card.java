package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

/**
 * Card representation
 *
 * @author mcrj
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "consumer")
@ToString(exclude = "consumer")
public class Card {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private Long number;

    private BigDecimal balance;

    @Enumerated(STRING)
    private CardType cardType;

    @JsonIgnore
    @ManyToOne
    private Consumer consumer;

}
