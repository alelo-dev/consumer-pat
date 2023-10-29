package br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer;


import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.mapper.CardCustomerMapper;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardCustomerRequest;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.request.CardTypeEnum;
import br.com.alelo.consumer.consumerpat.adapters.in.controller.cardcustomer.response.CardCustomerResponse;
import br.com.alelo.consumer.consumerpat.application.core.domain.cardcustomer.CardCustomer;
import br.com.alelo.consumer.consumerpat.application.ports.in.cardcustomer.FindCardCustomerInputPort;
import br.com.alelo.consumer.consumerpat.application.ports.in.cardcustomer.InsertCardCustomerInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CardCustomerControllerTest {
    @InjectMocks
    private CardCustomerController consumerCardController;

    @MockBean
    private InsertCardCustomerInputPort insertCardCustomerInputPort;
    @MockBean
    private FindCardCustomerInputPort findCardCustomerInputPort;

    @Mock
    private CardCustomerMapper cardCustomerMapper;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testCreateCardCustomerSuccess() throws Exception {
        UUID consumerId = UUID.randomUUID();
        var cardRequest = new CardCustomerRequest(CardTypeEnum.FOOD, "21312312", null);

        mockMvc.perform(post("/customer/{customerId}/card", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(cardRequest)))
                .andExpect(status().isNoContent());
    }

    @Test
    void testFindAllCardsByCustomerIdSuccess() throws Exception {
        UUID consumerId = UUID.randomUUID();
        CardCustomer cardCustomer = new CardCustomer(consumerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), null);
        Set<CardCustomer> cardCustomersSet = new HashSet<>();
        cardCustomersSet.add(cardCustomer);

        var cardCustomerResponse = new CardCustomerResponse(consumerId, CardTypeEnum.FOOD.value(),"1234567890123456", BigDecimal.valueOf(300), null);


        when(findCardCustomerInputPort.findCardCustomerById(consumerId)).thenReturn(cardCustomersSet);

        mockMvc.perform(get("/customer/{consumerId}/card", consumerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].cardNumber").value(cardCustomerResponse.getCardNumber()))
                .andExpect(jsonPath("$.[0].cardType").value("FOOD"));
    }
}
