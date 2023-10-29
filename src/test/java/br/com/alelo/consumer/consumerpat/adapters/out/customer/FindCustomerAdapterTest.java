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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindCustomerAdapterTest {
    private FindCustomerAdapter findCustomerAdapter;
    private CustomerRepository customerRepository;
    private CustomerEntityMapper customerEntityMapper;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerEntityMapper = mock(CustomerEntityMapper.class);

        findCustomerAdapter = new FindCustomerAdapter(customerRepository, customerEntityMapper);
    }

    @Test
    void testFindAllCustomerWithSuccess() {
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);
        List<CustomerEntity> customers = List.of(customerEntity);

        Pageable pageable = PageRequest.of(0, 100);

        var customer = new Customer(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));

        when(customerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(customers));
        when(customerEntityMapper.toCustomer(any())).thenReturn(customer);


        List<Customer> resultList = findCustomerAdapter.findAllCustomer(pageable);

        assertEquals(List.of(customer), resultList);

        verify(customerRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testFindByIdWithSuccess() {
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);

        var customer = new Customer(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));

        when(customerRepository.findById(any())).thenReturn(Optional.of(customerEntity));
        when(customerEntityMapper.toCustomer(customerEntity)).thenReturn(customer);


        Optional<Customer> result = findCustomerAdapter.findCustomerById(UUID.randomUUID());

        assertEquals(Optional.of(customer), result);

        verify(customerRepository, times(1)).findById(any(UUID.class));
    }
}
