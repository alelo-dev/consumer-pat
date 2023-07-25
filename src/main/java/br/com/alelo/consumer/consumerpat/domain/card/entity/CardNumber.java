package br.com.alelo.consumer.consumerpat.domain.card.entity;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CardNumber {
    @ValidateCardNumber
    @NotNull(message = "Card number is required")
    private String cardNumber;

    public CardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
