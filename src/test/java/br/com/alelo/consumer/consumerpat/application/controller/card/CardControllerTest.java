package br.com.alelo.consumer.consumerpat.application.controller.card;

import br.com.alelo.consumer.consumerpat.application.controller.card.request.CardRechargeRequest;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.service.CardService;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CardControllerTest {

    @InjectMocks
    private CardController cardController;

    @Mock
    private CardService cardService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testRechargeCard_Success() throws Exception {
        CardRechargeRequest request = new CardRechargeRequest(new CardNumber("1234567890123456"), BigDecimal.valueOf(100.00));

        mockMvc.perform(post("/cards/recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());

        then(cardService).should().chargeCard(request.getCardNumber(), request.getAmount());
    }


    @Test
    void testRechargeCard_InvalidRequest() throws Exception {
        CardRechargeRequest request = new CardRechargeRequest(null, BigDecimal.valueOf(100));

        mockMvc.perform(post("/cards/recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(cardService);
    }

    @Test
    void testRechargeCard_CardServiceThrowsException() throws Exception {
        CardNumber cardNumber = new CardNumber("1234567890123456");
        BigDecimal amount = BigDecimal.valueOf(100.00);
        CardRechargeRequest request = new CardRechargeRequest(cardNumber, amount);

        doThrow(new DomainException("Error charging card"))
                .when(cardService)
                .chargeCard(cardNumber, amount);

        mockMvc.perform(post("/cards/recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());

        then(cardService).should().chargeCard(cardNumber, amount);
    }

    @Test
    void testRechargeCard_CardServiceThrowsNotFoundException() throws Exception {
        CardNumber cardNumber = new CardNumber("1234567890123456");
        BigDecimal amount = BigDecimal.valueOf(100.00);
        CardRechargeRequest request = new CardRechargeRequest(cardNumber, amount);

        doThrow(new ResourceNotFoundException("Card " + cardNumber + " not found"))
                .when(cardService)
                .chargeCard(cardNumber, amount);

        mockMvc.perform(post("/cards/recharge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());

        then(cardService).should().chargeCard(cardNumber, amount);
    }
}
