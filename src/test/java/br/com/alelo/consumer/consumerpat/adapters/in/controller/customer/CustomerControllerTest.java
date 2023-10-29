package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer;


import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.AddressRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.ContactRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.request.CustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.response.CustomerResponse;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Address;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Contact;
import br.com.alelo.consumer.consumerpat.application.core.domain.customer.Customer;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.FindCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.InsertCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.in.customer.UpdateCustomerInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @MockBean
    private InsertCustomerInputPort insertCustomerInputPort;

    @MockBean
    private FindCustomerInputPort findCustomerInputPort;

    @MockBean
    private UpdateCustomerInputPort updateCustomerInputPort;


    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testFindAllCustomersSuccess() throws Exception {
        Customer customer = new Customer(UUID.randomUUID(),"Joao das neves", "111111111", LocalDate.of(1993, 4, 2),
                new Address("Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000"),
                new Contact("940028786", null, "joaodasneves@gmail.com"));
        List<Customer> customers = List.of(customer);

        when(findCustomerInputPort.findAllCustomers(any(Pageable.class))).thenReturn(customers);


        mockMvc.perform(get("/customer")
                        .param("page", "0")
                        .param("size", "100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value(customer.getName()))
                .andExpect(jsonPath("$.content[0].documentNumber").value(customer.getDocumentNumber()))
                .andExpect(jsonPath("$.content[0].contact.mobilePhoneNumber").value(customer.getContact().getMobilePhoneNumber()))
                .andExpect(jsonPath("$.content[0].address.street").value(customer.getAddress().getStreet()));

    }

    @Test
    void testFindCustomerSuccess() throws Exception {
        // Prepare test data
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(UUID.randomUUID(),"Joao das neves", "111111111", LocalDate.of(1993, 4, 2),
                new Address("Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000"),
                new Contact("940028786", null, "joaodasneves@gmail.com"));

        // Mock the service method
        when(findCustomerInputPort.findCustomerById(customerId)).thenReturn(Optional.of(customer));

        MvcResult result = mockMvc.perform(get("/customer/{customerId}", customerId))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("Result: " + result.getResponse().getContentAsString());

        CustomerResponse consumerResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CustomerResponse.class);

        assertThat(consumerResponse.getName()).isEqualTo(customer.getName());
        assertThat(consumerResponse.getDocumentNumber()).isEqualTo(customer.getDocumentNumber());
        assertThat(consumerResponse.getBirthDate().toString()).hasToString(customer.getBirthDate().toString());
        assertThat(consumerResponse.getContact().getResidencePhoneNumber()).isEqualTo(customer.getContact().getResidencePhoneNumber());
    }

    @Test
    void testCreateConsumer_Success() throws Exception {
        var contactRequest = new ContactRequest ("940028786", "312312","joaodasneves@gmail.com");
        var addressRequest = new AddressRequest("Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000");
        var customerRequest = new CustomerRequest("Joao das neves", "111111111", LocalDate.now(), addressRequest, contactRequest);

        // Mock the service method
        doNothing().when(insertCustomerInputPort).insert(any(Customer.class));


        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isOk());
    }


    @Test
    void testFindCustomerNotFound() throws Exception {
        UUID consumerId = UUID.randomUUID();

        when(findCustomerInputPort.findCustomerById(consumerId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/customer/{customerId}", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateConsumer_Success() throws Exception {
        UUID customerId = UUID.randomUUID();
        var contactRequest = new ContactRequest ("940028786", "312312","joaodasneves@gmail.com");
        var addressRequest = new AddressRequest("Avenida Euclides da cunha", "3", "Dracena", "Brasil", "03343000");
        var customerRequest = new CustomerRequest("Joao das neves", "111111111", LocalDate.now(), addressRequest, contactRequest);

        mockMvc.perform(put("/customer/{customerId}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequest)))
                .andExpect(status().isNoContent());
    }
}
