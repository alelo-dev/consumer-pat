package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class BuyRequest {
    private CardBuyRequest card;
    @NotNull @NotEmpty
    private String idEstablishment;
    @NotNull @NotEmpty
    private String productDescription;
    @NotNull
    private BigDecimal value;
}
