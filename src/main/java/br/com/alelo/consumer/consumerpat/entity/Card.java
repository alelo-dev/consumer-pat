package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Entity
@Data
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private BigDecimal balance;

    @ManyToOne(targetEntity = CardType.class)
    @JoinColumn(name = "card_type_id")
    private CardType CardType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private Consumer consumer;
}