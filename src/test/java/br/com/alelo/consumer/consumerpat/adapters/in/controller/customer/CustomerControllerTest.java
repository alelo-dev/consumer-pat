package br.com.alelo.consumer.consumerpat.adapters.in.controller.customer;


import br.com.alelo.consumer.consumerpat.adapters.in.controller.customer.CustomerController;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

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
        // Prepare test data
        Customer customer = new Customer(UUID.randomUUID(),"John Doe", "123456789", LocalDate.of(1990, 1, 1),
                new Address("123 Main St", "1", "City", "Country", "12345"),
                new Contact("123456789", null, "john.doe@example.com"));
        List<Customer> customers = List.of(customer);

        // Mock the service method
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
        UUID consumerId = UUID.randomUUID();
        Customer customer = new Customer(consumerId,"Jane Smith", "987654321", LocalDate.of(1985, 5, 15),
                new Address("456 Oak St", "2", "City", "Country", "54321"),
                new Contact("12331231", "987654321", "jane.smith@example.com"));

        // Mock the service method
        when(findCustomerInputPort.findCustomerById(eq(consumerId))).thenReturn(Optional.of(customer));

        MvcResult result = mockMvc.perform(get("/customer/{customerId}", consumerId))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("Result: " + result.getResponse().getContentAsString());

        CustomerResponse consumerResponse = objectMapper.readValue(result.getResponse().getContentAsString(), CustomerResponse.class);

        assertThat(consumerResponse.getName()).isEqualTo(customer.getName());
        assertThat(consumerResponse.getDocumentNumber()).isEqualTo(customer.getDocumentNumber());
        assertThat(consumerResponse.getBirthDate().toString()).isEqualTo(customer.getBirthDate().toString());
        assertThat(consumerResponse.getContact().getResidencePhoneNumber()).isEqualTo(customer.getContact().getResidencePhoneNumber());
    }



    @Test
    void testFindCustomerNotFound() throws Exception {
        UUID consumerId = UUID.randomUUID();

        when(findCustomerInputPort.findCustomerById(eq(consumerId))).thenReturn(Optional.empty());

        mockMvc.perform(get("/customer/{customerId}", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }
}
