package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CardUpdateBalanceDTO {
    @NotNull
    private Integer cardNumber;

    @Positive
    @NotNull
    private Double cardBalance;

    @NotNull
    private CardType cardType;
}
