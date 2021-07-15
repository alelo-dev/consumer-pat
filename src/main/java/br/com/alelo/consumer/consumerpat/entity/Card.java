package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.enums.TypeCard;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Card extends BaseEntity {

    @NotNull
    private Long cardNumber;

    @NotNull
    private BigDecimal cardBalance;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeCard typeCard;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;
}
