package br.com.alelo.consumer.consumerpat.adapters.out.customer.mapper;

import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.AddressEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.ContactEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.entity.CustomerEntity;
import br.com.alelo.consumer.consumerpat.adapters.out.customer.repository.mapper.CustomerEntityMapper;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerEntityMapperTest {

    @Test
    void testToCustomerEntity() {
        var customer = new Customer(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new Contact("22222222222", null, "joaodasneves@gmail.com"));

        CustomerEntityMapper mapper = new CustomerEntityMapper();

        CustomerEntity customerEntity = mapper.toCustomerEntity(customer);

        assertEquals(customer.getId(), customerEntity.getId());
        assertEquals(customer.getName(), customerEntity.getName());
        assertEquals(customer.getAddress().getStreet(), customerEntity.getAddress().getStreet());
        assertEquals(customer.getAddress().getCity(), customerEntity.getAddress().getCity());
        assertEquals(customer.getContact().getEmail(), customerEntity.getContact().getEmail());
        assertEquals(customer.getContact().getMobilePhoneNumber(), customerEntity.getContact().getMobilePhoneNumber());
    }

    @Test
    void testToCustomer() {
        var customerEntity = new CustomerEntity(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                new AddressEntity(UUID.randomUUID(),"Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000"),
                new ContactEntity(UUID.randomUUID(),"22222222222", null, "joaodasneves@gmail.com"), LocalDateTime.now(), null);

        CustomerEntityMapper mapper = new CustomerEntityMapper();

        Customer customer = mapper.toCustomer(customerEntity);

        assertEquals(customerEntity.getId(), customer.getId());
        assertEquals(customerEntity.getName(), customer.getName());
        assertEquals(customerEntity.getAddress().getStreet(), customer.getAddress().getStreet());
        assertEquals(customerEntity.getAddress().getCity(), customer.getAddress().getCity());
        assertEquals(customerEntity.getContact().getEmail(), customer.getContact().getEmail());
        assertEquals(customerEntity.getContact().getMobilePhoneNumber(), customer.getContact().getMobilePhoneNumber());
    }
}
