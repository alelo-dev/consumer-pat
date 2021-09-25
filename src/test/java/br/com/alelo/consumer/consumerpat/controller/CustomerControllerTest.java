package br.com.alelo.consumer.consumerpat.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.Customer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @Test
    public void whenGetListCustomer_thenReturnCorrect() throws Exception {
        List<Customer> customers = createCustomerList();
        when(service.listAll(any())).thenReturn(new PageImpl<>(customers));

        this.mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(customers.size())))
                .andExpect(jsonPath("$.result.size()", is(customers.size())));
    }

    @Test
    public void whenGetEmptytListCustomer_thenReturnCorrect() throws Exception {
        when(service.listAll(any())).thenReturn(Page.empty());

        this.mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(0)))
                .andExpect(jsonPath("$.result.size()", is(0)));
    }

    @Test
    public void whenFindOneExistingCustomer_thenReturnCorrect() throws Exception {
        when(service.findById(any())).thenReturn(Optional.of(createCustomer(15L, "005478")));

        this.mockMvc.perform(get("/customer/15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(15)))
                .andExpect(jsonPath("$.documentNumber", is("005478")))
                .andExpect(jsonPath("$.birthDate", is(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE))))
                .andExpect(jsonPath("$.mobilePhoneNumber", is("mobilePhoneNumber")))
                .andExpect(jsonPath("$.residencePhoneNumber", is("residencePhoneNumber")))
                .andExpect(jsonPath("$.phoneNumber", is("phoneNumber")))
                .andExpect(jsonPath("$.email", is("email")))
                .andExpect(jsonPath("$.street", is("street")))
                .andExpect(jsonPath("$.number", is(50)))
                .andExpect(jsonPath("$.city", is("city")))
                .andExpect(jsonPath("$.country", is("country")))
                .andExpect(jsonPath("$.portalCode", is("portalCode")));
    }

    @Test
    public void whenFindOneNonExistingCustomer_thenReturnNotFound() throws Exception {
        when(service.findById(15L)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/customer/15"))
                .andExpect(status().isNotFound());
    }

    private List<Customer> createCustomerList() {

        return List.of(
                createCustomer(1L, "00001"),
                createCustomer(2L, "00002"),
                createCustomer(3L, "00003"),
                createCustomer(4L, "00004"),
                createCustomer(5L, "00005"),
                createCustomer(6L, "00006"),
                createCustomer(7L, "00007"),
                createCustomer(8L, "00008"),
                createCustomer(9L, "00009"),
                createCustomer(10L, "00010")
        );
    }

    private Customer createCustomer(Long id, String documentNumber) {
        return Customer.builder()
                .id(id)
                .documentNumber(documentNumber)
                .birthDate(LocalDate.now())
                .mobilePhoneNumber("mobilePhoneNumber")
                .residencePhoneNumber("residencePhoneNumber")
                .phoneNumber("phoneNumber")
                .email("email")
                .street("street")
                .number(50)
                .city("city")
                .country("country")
                .portalCode("portalCode")
                .build();
    }

}
