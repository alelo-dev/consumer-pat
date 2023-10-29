package br.com.alelo.consumer.consumerpat.adapters.out.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.CustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.mapper.CustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InsertCustomerAdapterTest {

    private InsertCustomerAdapter insertCustomerAdapter;
    private CustomerRepository customerRepository;
    private CustomerEntityMapper customerEntityMapper;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerEntityMapper = mock(CustomerEntityMapper.class);

        insertCustomerAdapter = new InsertCustomerAdapter(customerRepository, customerEntityMapper);
    }

    @Test
    void testInsertCustomerWithSuccess() {
        var customer = new Customer(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);

        when(customerEntityMapper.toCustomerEntity(customer)).thenReturn(customerEntity);

        insertCustomerAdapter.insert(customer);

        verify(customerRepository).save(customerEntity);
    }
}
