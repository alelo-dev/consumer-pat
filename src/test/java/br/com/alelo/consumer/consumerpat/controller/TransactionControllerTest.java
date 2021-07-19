package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.dto.BuyCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    private final String baseUrl = "http://localhost:%s/%s/%s";
    @MockBean
    TransactionService transactionService;
    @MockBean
    CardRepository cardRepository;
    @MockBean
    ConsumerRepository consumerRepository;
    @MockBean
    ExtractRepository extractRepository;
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenSetBalanceIsCalled_ShouldReturnsNewBalanceValueAns200() throws Exception {
        String uri = String.format(baseUrl, 8080, "transaction", "setcardbalance");

        BigDecimal addValue = BigDecimal.valueOf(1000.00);

        CardBalanceDTO cardBalanceDTO = CardBalanceDTO.builder()
                .cardNumber("132456789")
                .value(BigDecimal.valueOf(1234.12).add(addValue))
                .build();

        when(transactionService.addBalance(any())).thenReturn(addValue.add(cardBalanceDTO.getValue()));

        MvcResult map = this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cardBalanceDTO).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();

        Map<String, Double> map1 = new HashMap(mapper.readValue(map.getResponse().getContentAsString(), Map.class));
        Map.Entry<String, Double> entry = map1.entrySet().iterator().next();
        String key = entry.getKey();
        Double value = entry.getValue();

        assertEquals(BigDecimal.valueOf(value), addValue.add(cardBalanceDTO.getValue()));
    }

    @Test
    public void whenBuyIsCalled_ShouldReturns200() throws Exception {
        String uri = String.format(baseUrl, 8080, "transaction", "buy");

        BuyCardBalanceDTO buyCardBalanceDTO = new BuyCardBalanceDTO();

        doNothing().when(transactionService).processBuy(any());

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buyCardBalanceDTO).getBytes(StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();
    }
}










