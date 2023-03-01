package br.com.alelo.consumer.consumerpat.recharge.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.CardNumber;
import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class NewRecharge {

    @CardNumber
    @NotEmpty(message = "Card number is required.")
    String cardNumber;

    @NotNull(message = "Amount is required.")
    @DecimalMin(value = "0.01" , message = "Payment must have a valid amount.")
    BigDecimal amount;

    @FutureOrPresent(message = "Recharge date cannot be on the past.")
    @NotNull(message = "Recharge date is required.")
    LocalDate rechargedAt;
}
