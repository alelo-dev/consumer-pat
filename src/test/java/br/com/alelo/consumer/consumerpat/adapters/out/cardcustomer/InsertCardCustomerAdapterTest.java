package br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.CardCustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.entity.CardCustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.cardcustomer.repository.mapper.CardCustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.CustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InsertCardCustomerAdapterTest {

    private InsertCardCustomerAdapter insertCardCustomerAdapter;
    private CardCustomerRepository cardCustomerRepository;
    private CustomerRepository customerRepository;
    private CardCustomerEntityMapper cardCustomerEntityMapper;

    @BeforeEach
    void setUp() {
        cardCustomerRepository = mock(CardCustomerRepository.class);
        customerRepository = mock(CustomerRepository.class);
        cardCustomerEntityMapper = mock(CardCustomerEntityMapper.class);

        insertCardCustomerAdapter = new InsertCardCustomerAdapter(cardCustomerRepository, customerRepository, cardCustomerEntityMapper);
    }


    @Test
    void testInsertCardCustomer() {
        UUID customerId = UUID.randomUUID();
        var cardCustomer = new CardCustomer(customerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), null);
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);

        var cardCurtomerEntity = new CardCustomerEntity(customerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), customerEntity);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));
        when(cardCustomerEntityMapper.toCardCustomerEntity(cardCustomer)).thenReturn(cardCurtomerEntity);

        insertCardCustomerAdapter.insert(customerId, cardCustomer);

        verify(cardCustomerEntityMapper).toCardCustomerEntity(cardCustomer);
        verify(cardCustomerEntityMapper).toCardCustomerEntity(cardCustomer);
        verify(cardCustomerRepository).save(cardCurtomerEntity);
    }
}
