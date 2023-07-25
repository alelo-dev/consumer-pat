package br.com.alelo.consumer.consumerpat.domain.payment.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.payment.entity.Establishment;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PaymentService {
    void registerPayment(Establishment establishment,
                         String productDescription,
                         LocalDate buyDate,
                         CardNumber cardNumber,
                         BigDecimal amount);

}
