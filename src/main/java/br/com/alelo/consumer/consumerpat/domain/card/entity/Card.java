package br.com.alelo.consumer.consumerpat.domain.card.entity;

import br.com.alelo.consumer.consumerpat.domain.consumer.entity.Consumer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Card {

    @Valid
    @NotNull(message = "cardNumber is required")
    private CardNumber cardNumber;
    @NotNull(message = "cardType is required")
    private CardType cardType;
    @JsonIgnore
    private Consumer consumer;
    @JsonIgnore
    private CardBalance cardBalance;

    public Card(CardNumber cardNumber, CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public void addConsumer(final Consumer consumer) {
        this.consumer = consumer;
    }

    public void addCardBalance(final CardBalance cardBalance) {
        this.cardBalance = cardBalance;
    }

}
