package br.com.alelo.consumer.consumerpat.recharge.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.CardNumber;
import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value(staticConstructor = "of")
public class RechargeCommand {

    @CardNumber
    @NotEmpty(message = "Card number is required.")
    String cardNumber;

    @NotNull(message = "Recharge amount is required.")
    @DecimalMin(value = "0.01", message = "Recharge amount must be positive.")
    BigDecimal amount;

    @NotNull(message = "Recharge date is required.")
    LocalDate rechargedAt;
}
