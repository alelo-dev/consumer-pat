package br.com.alelo.consumer.consumerpat.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cardCode"))
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cardCode;
    private Long number;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CardType type;

}
