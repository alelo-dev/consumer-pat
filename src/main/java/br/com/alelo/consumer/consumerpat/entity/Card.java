package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.TypeCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@SuperBuilder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Card extends BaseEntity {

    @Column(nullable = false)
    private Long cardNumber;

    @Column(nullable = false)
    private BigDecimal cardBalance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCard typeCard;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;
}
