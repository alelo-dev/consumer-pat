package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.mapper.CardCustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindCardCustomerAdapterTest {

    private FindCardCustomerAdapter findCardCustomerAdapter;
    private CardCustomerRepository cardCustomerRepository;
    private CardCustomerEntityMapper cardCustomerEntityMapper;

    @BeforeEach
    void setUp() {
        cardCustomerRepository = mock(CardCustomerRepository.class);
        cardCustomerEntityMapper = mock(CardCustomerEntityMapper.class);

        findCardCustomerAdapter = new FindCardCustomerAdapter(cardCustomerRepository, cardCustomerEntityMapper);
    }

    @Test
    void testFindCardCustomerById() {
        UUID customerId = UUID.randomUUID();

        var cardCustomer = new CardCustomer(customerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), null);

        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);

        var cardCurtomerEntity = new CardCustomerEntity(customerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), customerEntity);
        Set<CardCustomerEntity> cardCustomers = Set.of(cardCurtomerEntity);

        when(cardCustomerRepository.findByCustomerId(customerId)).thenReturn(cardCustomers);
        when(cardCustomerEntityMapper.toCardCustomer(any())).thenReturn(cardCustomer);

        Set<CardCustomer> result = findCardCustomerAdapter.findCardCustomerById(customerId);

        assertEquals(Set.of(cardCustomer), result);
    }
}
