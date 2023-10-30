package br.com.alelo.consumer.consumerpat.adapters.out.customer;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.CustomerRepository;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateCustomerAdapterTest {

    private UpdateCustomerAdapter updateCustomerAdapter;
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);

        updateCustomerAdapter = new UpdateCustomerAdapter(customerRepository);
    }

    @Test
    void testUpdateCustomerWhenFound() {
        UUID customerId = UUID.randomUUID();
        var customer = new Customer(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);


        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customerEntity));

        updateCustomerAdapter.update(customerId, customer);

        verify(customerRepository).save(customerEntity);
    }

    @Test
    void testUpdateCustomerWhenNotFound() {
        UUID customerId = UUID.randomUUID();
        var customer = new Customer(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        updateCustomerAdapter.update(customerId, customer);

        verify(customerRepository, never()).save(any(CustomerEntity.class));
    }
}
