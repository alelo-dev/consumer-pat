package br.com.alelo.consumer.consumerpat.common.domain;

import br.com.alelo.consumer.consumerpat.consumer.domain.Consumer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    private String number;

    @JoinColumn(name = "card_type_id")
    @ManyToOne
    private CardType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer holder;

    private boolean active;
}
