package br.com.alelo.consumer.consumerpat.domain.entity;

import br.com.alelo.consumer.consumerpat.domain.enumeration.CardType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@Entity
public class Card extends EntidadeBase<Integer> {

    @Id
    private Integer cardNumber;

    @Column(name = "balanceValue")
    private BigDecimal balanceValue;

    @Column(name = "cardTypeId")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

}
