package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class CardBuyRequest {
    @NotNull @NotEmpty
    private Long cardNumber;
    private String typeCard;
}
