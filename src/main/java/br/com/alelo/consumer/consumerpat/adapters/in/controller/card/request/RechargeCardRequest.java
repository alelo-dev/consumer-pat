package br.com.alelo.consumer.consumerpat.adapters.in.controller.card.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RechargeCardRequest {
    @NotBlank(message = "card number is required")
    private String cardNumber;

    @NotNull(message = "amount is required")
    private BigDecimal amount;
}
