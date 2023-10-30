package br.com.alelo.consumer.consumerpat.adapters.out.payments.mapper;

import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.entity.PaymentsEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.payments.repository.mapper.PaymentsEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Establishment;
import br.com.alelo.consumer.consumerpat.application.core.domain.payments.Payments;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentsEntityMapperTest {

    @Test
    void testToPaymentsEntity() {
        var cardNumber = "32312312312312312312";
        var cardType = "DRUGSTORE";
        Establishment establishment = new Establishment("Raia", cardType);;
        var payments = new Payments(UUID.randomUUID(), establishment, "Pharmacy", LocalDate.now(), cardNumber, BigDecimal.valueOf(150));

        PaymentsEntityMapper mapper = new PaymentsEntityMapper();

        PaymentsEntity paymentsEntity = mapper.toPaymentsEntity(payments);

        assertEquals(payments.getId(), paymentsEntity.getId());
        assertEquals(payments.getAmount(), paymentsEntity.getAmount());
        assertEquals(payments.getEstablishment().getEstablishmentName(), paymentsEntity.getEstablishment().getEstablishmentName());
    }
}
