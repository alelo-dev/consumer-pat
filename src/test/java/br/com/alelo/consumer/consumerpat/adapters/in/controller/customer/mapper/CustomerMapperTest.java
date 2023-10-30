package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.mapper;

import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.AddressRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.ContactRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.CustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.CustomerResponse;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    @Test
    void testToCustomer() {
        var addressRequest = new AddressRequest("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000");
        var contactRequest = new ContactRequest("22222222222", null, "joaodasneves@gmail.com");
        var customerRequest = new CustomerRequest("Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                addressRequest, contactRequest);

        CustomerMapper mapper = new CustomerMapper();

        Customer customer = mapper.toCustomer(customerRequest);

        assertEquals(customerRequest.getName(), customer.getName());
        assertEquals(addressRequest.getStreet(), customer.getAddress().getStreet());
        assertEquals(addressRequest.getCity(), customer.getAddress().getCity());
        assertEquals(contactRequest.getEmail(), customer.getContact().getEmail());
        assertEquals(contactRequest.getMobilePhoneNumber(), customer.getContact().getMobilePhoneNumber());
    }

    @Test
    void testToCustomerResponse() {
        var address = new Address("Avenida Euclides da cunha", "1", "Dracena", "Brasil", "03343000");
        var contact = new Contact("22222222222", null, "joaodasneves@gmail.com");
        var customer = new Customer(UUID.randomUUID(),"Joao das neves", "22222222222", LocalDate.of(1990, 1, 1),
                address, contact);

        CustomerMapper mapper = new CustomerMapper();

        CustomerResponse customerResponse = mapper.toCustomerResponse(customer);

        assertEquals(customer.getId(), customerResponse.getId());
        assertEquals(customer.getName(), customerResponse.getName());
        assertEquals(address.getStreet(), customerResponse.getAddress().getStreet());
        assertEquals(address.getCity(), customerResponse.getAddress().getCity());
        assertEquals(contact.getEmail(), customerResponse.getContact().getEmail());
        assertEquals(contact.getMobilePhoneNumber(), customerResponse.getContact().getMobilePhoneNumber());
    }
}
