package br.com.alelo.consumer.consumerpat.domain.card.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CardNumber {
    @ValidateCardNumber
    @NotNull(message = "Card number is required")
    private String cardNumber;

}
