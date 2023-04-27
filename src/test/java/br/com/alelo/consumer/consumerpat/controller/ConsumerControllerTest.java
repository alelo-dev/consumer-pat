package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.service.BalanceService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ConsumerController.class)
class ConsumerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConsumerService consumerService;

    @MockBean
    private BalanceService balanceService;

    @Test
    void whenValidInputToInsertConsumer_thenReturns200() throws Exception {
        String consumerJson = "{ \"id\": 0,\"name\": \"Teste\",\"documentNumber\": 0,\"birthDate\": \"2023-04-26T20:29:02.630Z\",\"mobilePhoneNumber\": 0,\"residencePhoneNumber\": 0,\"phoneNumber\": 0,\"email\": \"string\",\"street\": \"string\",\"number\": 0,\"city\": \"POA\",\"country\": \"string\",\"portalCode\": 0,\"foodCardNumber\": 123456789011,\"foodCardBalance\": 10.1,\"fuelCardNumber\": 123456789012,\"fuelCardBalance\": 10.1,\"drugstoreNumber\": 123456789013,\"drugstoreCardBalance\": 10.1}";
        mockMvc.perform(post("/consumer")
                        .content(consumerJson)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

}