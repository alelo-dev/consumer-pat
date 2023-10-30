package br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.mapper;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardCustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.response.CardCustomerResponse;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardCustomerMapperTest {
    @Test
    void testToCardCustomer() {
        var cardCustomerRequest = new CardCustomerRequest(CardTypeEnum.FOOD,"1234567890123456", null);

        CardCustomerMapper mapper = new CardCustomerMapper();

        CardCustomer cardCustomer = mapper.toCardCustomer(cardCustomerRequest);

        assertEquals(cardCustomerRequest.getCardNumber(), cardCustomer.getCardNumber());
        assertEquals(cardCustomerRequest.getCardType().value(), cardCustomer.getCardType());
    }

    @Test
    void testToCardCustomerResponse() {
        UUID customerId = UUID.randomUUID();
        var customer = new Customer(customerId,"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));
        var cardCustomer = new CardCustomer(customerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), customer);

        CardCustomerMapper mapper = new CardCustomerMapper();

        CardCustomerResponse cardCustomerResponse = mapper.toCardCustomerResponse(cardCustomer);

        assertEquals(cardCustomer.getId(), cardCustomerResponse.getId());
        assertEquals(cardCustomer.getCardType(), cardCustomerResponse.getCardType());
        assertEquals(cardCustomer.getCustomer().getId(), cardCustomerResponse.getCustomer().getId());
        assertEquals(cardCustomer.getCustomer().getContact().getEmail(), cardCustomerResponse.getCustomer().getContact().getEmail());
    }
}
