package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PaymentRequest {

    @Valid
    @NotNull(message = "establishment is required")
    private EstablishmentRequest establishment;
    @NotBlank(message = "productDescription is required")
    private String productDescription;
    @NotNull(message = "buyDate is required")
    private LocalDate buyDate;
    @Valid
    @NotBlank(message = "cardNumber is required")
    private String cardNumber;
    @NotNull(message = "amount is required")
    private BigDecimal amount;
}
