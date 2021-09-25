package br.com.alelo.consumer.consumerpat.controller;

import static br.com.alelo.consumer.consumerpat.enums.ProductType.FOOD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import br.com.alelo.consumer.consumerpat.dto.CardDto;
import br.com.alelo.consumer.consumerpat.dto.RechargeDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Customer;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @MockBean
    private CustomerService customerService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void whenInsertCardForValidCustomer_thenReturnSuccess() throws Exception {
        when(customerService.findById(10L)).thenReturn(Optional.of(Customer.builder().build()));

        this.mockMvc.perform(
                post("/customer/10/card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                CardDto.builder()
                                        .number("XXXXXX")
                                        .productId(FOOD.getId())
                                        .build())
                        )
        )
                .andExpect(status().isOk());
    }

    @Test
    public void whenInsertCardForInvalidCustomer_thenReturnCustomerNotFound() throws Exception {
        when(customerService.findById(10L)).thenReturn(Optional.empty());

        this.mockMvc.perform(
                post("/customer/10/card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CardDto.builder().build()))
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found"));
    }

    @Test
    public void whenRechargeCardForValidCardAndCustomer_thenReturnSuccess() throws Exception {
        when(customerService.findById(10L)).thenReturn(Optional.of(Customer.builder().build()));
        when(cardService.findByNumberAndCustomer(eq("XXXYXX"), any(Customer.class))).thenReturn(Optional.of(Card.builder().build()));

        this.mockMvc.perform(
                patch("/customer/10/card/recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                RechargeDto.builder()
                                        .cardNumber("XXXYXX")
                                        .value(10)
                                        .build())
                        )
        )
                .andExpect(status().isOk());
    }

    @Test
    public void whenRechargeCardForValidCardAndInvalidCustomer_thenReturnCustomerNotFound() throws Exception {
        when(customerService.findById(10L)).thenReturn(Optional.empty());
        when(cardService.findByNumberAndCustomer(eq("XXXYXX"), any(Customer.class))).thenReturn(Optional.of(Card.builder().build()));

        this.mockMvc.perform(
                patch("/customer/10/card/recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                RechargeDto.builder()
                                        .cardNumber("XXXYXX")
                                        .value(10)
                                        .build())
                        )
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found"));
    }

    @Test
    public void whenRechargeCardForInvalidCardAndValidCustomer_thenReturnCardNotFound() throws Exception {
        when(customerService.findById(10L)).thenReturn(Optional.of(Customer.builder().build()));
        when(cardService.findByNumberAndCustomer(eq("XXXYXX"), any(Customer.class))).thenReturn(Optional.empty());

        this.mockMvc.perform(
                patch("/customer/10/card/recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                RechargeDto.builder()
                                        .cardNumber("XXXYXX")
                                        .value(10)
                                        .build())
                        )
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Card not found"));
    }

}
