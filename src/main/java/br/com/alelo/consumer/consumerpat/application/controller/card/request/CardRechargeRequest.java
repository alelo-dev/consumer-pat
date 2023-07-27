package br.com.alelo.consumer.consumerpat.application.controller.card.request;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardRechargeRequest {

    @Valid
    @NotNull(message = "CardNumber is required.")
    private CardNumber cardNumber;

    @NotNull(message = "Amount is required.")
    private BigDecimal amount;
}
