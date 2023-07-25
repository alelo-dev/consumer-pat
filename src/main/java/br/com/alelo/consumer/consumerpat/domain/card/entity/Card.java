package br.com.alelo.consumer.consumerpat.domain.card.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Card {

    @NotNull(message = "Card number is required")
    private CardNumber cardNumber;
    @NotNull(message = "Card type number is required")
    private CardType cardType;
    @NotNull(message = "Consumer id number is required")
    private UUID consumerId;
    private CardBalance cardBalance;

    public Card(CardNumber cardNumber, CardType cardType, UUID consumerId) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.consumerId = consumerId;
    }

    public void addCardBalance(final CardBalance cardBalance) {
        this.cardBalance = cardBalance;
    }

}
