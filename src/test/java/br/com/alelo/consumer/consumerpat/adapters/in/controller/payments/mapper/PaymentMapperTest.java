package br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.mapper;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.EstablishmentRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.EstablishmentTypeEnum;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.payments.request.PaymentRequest;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentMapperTest {

    @Test
    void testToPayment() {
        var establishment = new EstablishmentRequest("Sample Restaurant", EstablishmentTypeEnum.FOOD);
        String productDescription = "Sample Product";
        var buyDate = LocalDate.now();
        BigDecimal amount = BigDecimal.valueOf(100);
        PaymentRequest paymentRequest = new PaymentRequest(establishment, productDescription, buyDate, "1234567890123456", amount);

        PaymentMapper mapper = new PaymentMapper();

        Payments payments = mapper.toPayment(paymentRequest);

        assertEquals(paymentRequest.getAmount(), payments.getAmount());
        assertEquals(paymentRequest.getProductDescription(), payments.getProductDescription());
        assertEquals(paymentRequest.getBuyDate(), payments.getBuyDate());
        assertEquals(paymentRequest.getEstablishment().getEstablishmentType().value(), payments.getEstablishment().getEstablishmentType());
        assertEquals(paymentRequest.getEstablishment().getEstablishmentName(), payments.getEstablishment().getEstablishmentName());
    }
}
