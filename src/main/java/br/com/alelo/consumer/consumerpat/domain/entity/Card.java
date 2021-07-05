package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "number"))
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long number;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CardType type;

}
