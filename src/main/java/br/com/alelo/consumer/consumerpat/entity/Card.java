package br.com.alelo.consumer.consumerpat.entity;

import br.com.alelo.consumer.consumerpat.converters.CardTypeConverter;
import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class Card implements Serializable {

    @Id
    private int cardNumber;

    @Column(name = "balanceValue")
    private BigDecimal balanceValue;

    @Column(name = "cardTypeId")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

}
