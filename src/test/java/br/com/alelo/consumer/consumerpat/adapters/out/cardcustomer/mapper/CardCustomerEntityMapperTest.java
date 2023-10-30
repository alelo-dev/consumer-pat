package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.mapper;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.mapper.CardCustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardCustomerEntityMapperTest {
    @Test
    void testToCardCustomerEntity() {
        UUID customerId = UUID.randomUUID();
        var customer = new Customer(customerId,"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));
        var cardCustomer = new CardCustomer(customerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), customer);


        CardCustomerEntityMapper mapper = new CardCustomerEntityMapper();

        CardCustomerEntity cardCustomerEntity = mapper.toCardCustomerEntity(cardCustomer);

        assertEquals(cardCustomer.getId(), cardCustomerEntity.getId());
        assertEquals(cardCustomer.getCardNumber(), cardCustomerEntity.getCardNumber());
        assertEquals(cardCustomer.getCardBalance(), cardCustomerEntity.getCardBalance());
        assertEquals(cardCustomer.getCustomer().getName(), cardCustomerEntity.getCustomer().getName());
        assertEquals(cardCustomer.getCustomer().getContact().getEmail(), cardCustomerEntity.getCustomer().getContact().getEmail());
        assertEquals(cardCustomer.getCustomer().getAddress().getCity(), cardCustomerEntity.getCustomer().getAddress().getCity());
    }

    @Test
    void testToCardCustomer() {
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);

        var cardCurtomerEntity = new CardCustomerEntity(UUID.randomUUID(), CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), customerEntity);

        CardCustomerEntityMapper mapper = new CardCustomerEntityMapper();

        CardCustomer cardCustomer = mapper.toCardCustomer(cardCurtomerEntity);

        assertEquals(cardCurtomerEntity.getId(), cardCustomer.getId());
        assertEquals(cardCurtomerEntity.getCustomer().getName(), cardCustomer.getCustomer().getName());
        assertEquals(cardCurtomerEntity.getCustomer().getBirthDate(), cardCustomer.getCustomer().getBirthDate());
        assertEquals(cardCurtomerEntity.getCustomer().getContact().getEmail(), cardCustomer.getCustomer().getContact().getEmail());
        assertEquals(cardCurtomerEntity.getCustomer().getAddress().getCity(), cardCustomer.getCustomer().getAddress().getCity());
    }
}
