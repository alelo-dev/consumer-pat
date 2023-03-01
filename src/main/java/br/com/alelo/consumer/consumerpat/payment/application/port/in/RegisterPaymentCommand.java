package br.com.alelo.consumer.consumerpat.payment.application.port.in;

import lombok.Value;

@Value(staticConstructor = "of")
public class RegisterPaymentCommand {

    NewPayment payment;
}
