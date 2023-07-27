package br.com.alelo.consumer.consumerpat.application.controller.payment.request;

import br.com.alelo.consumer.consumerpat.domain.payment.entity.Payment;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @NotNull(message = "Payment is required.")
    Payment payment;
}
