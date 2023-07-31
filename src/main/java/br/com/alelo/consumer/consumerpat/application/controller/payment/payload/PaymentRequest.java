package br.com.alelo.consumer.consumerpat.application.controller.payment.payload;

import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @Valid
    @NotNull(message = "Payment is required.")
    Payment payment;
}
