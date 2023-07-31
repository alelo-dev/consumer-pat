package br.com.alelo.consumer.consumerpat.application.controller.card.payload;

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
    @NotNull(message = "cardNumber is required.")
    private CardNumber cardNumber;

    @NotNull(message = "amount is required.")
    private BigDecimal amount;
}
