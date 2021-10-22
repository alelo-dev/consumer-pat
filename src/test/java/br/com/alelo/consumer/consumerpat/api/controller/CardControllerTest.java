package br.com.alelo.consumer.consumerpat.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.alelo.consumer.consumerpat.api.dto.input.CreditInput;
import br.com.alelo.consumer.consumerpat.domain.service.ManageBalanceTransactionsService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CardController.class)
@AutoConfigureMockMvc
public class CardControllerTest {

    static String CARD_API = "/api/cards";

    @MockBean
    ManageBalanceTransactionsService service;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Should add credit to card with sucess")
    public void addCreditTest() throws Exception {
        CreditInput creditInput = CreditInput.builder().cardNumber(1).value(new BigDecimal(100)).build();
        String json = new ObjectMapper().writeValueAsString(creditInput);

        BigDecimal creditResult = new BigDecimal(200);
        BDDMockito.given(service.addCreditToCard(Mockito.any(Integer.class), Mockito.any(BigDecimal.class)))
                .willReturn(creditResult);

        MockHttpServletRequestBuilder request = 
            MockMvcRequestBuilders
                .put(CARD_API.concat("/credit"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("balanceValue").value(creditResult));
    }
}
