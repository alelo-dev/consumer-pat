package br.com.alelo.consumer.consumerpat.payment.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.CardNumber;
import lombok.Value;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value(staticConstructor = "of")
public class NewPayment {

    @NotNull(message = "Establishment type is required.")
    Integer establishmentTypeId;

    @NotEmpty(message = "Establishment name is required.")
    String establishmentName;

    @CardNumber
    @NotEmpty(message = "Card number is required.")
    String cardNumber;

    @NotEmpty(message = "Description is required.")
    String description;

    @NotNull(message = "Amount is required.")
    @DecimalMin(value = "0.01" , message = "Payment must have a valid amount.")
    BigDecimal amount;

    @FutureOrPresent(message = "Payment cannot have a past billing date.")
    @NotNull(message = "Payment date is required.")
    LocalDate date;
}
