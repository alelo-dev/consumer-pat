package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ExtractService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static br.com.alelo.consumer.consumerpat.enums.CardType.FUEL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
class CardControllerTest extends AbstractControllerTest{

    private static final String CARD = "/card";
    private static final String BUY = "/buy";
    private static final String BALANCE = "/update-balance";
    private static final String PATH_VAR_CARD_NUMBER = "/{setCardBalance}";
    private static final String PATH_VAR_VALUE = "/{value}";

    private static final Long CARD_NUMBER = 1L;
    private static final BigDecimal VALUE_TEN = BigDecimal.TEN;

    @MockBean
    private CardService cardService;

    @MockBean
    private ExtractService extractService;

    @MockBean
    private ConsumerService consumerService;

    private BuyDTO buyDTOFuel;

    @BeforeEach
    public void setup() {
        buyDTOFuel = BuyDTO.builder()
                .establishmentType(FUEL)
                .cardNumber(CARD_NUMBER)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();
    }

    private Card getNewCard() {
        Card card = Card.builder()
                .id(3L)
                .cardType(FUEL)
                .number(30L)
                .balance(BigDecimal.valueOf(960))
                .build();

        return card;
    }

    @SneakyThrows
    @Test
    void Buy_when_request_is_ok() {
        this.mockMvc.perform(patch(CARD.concat(BUY))
                .content(objectMapper.writeValueAsString(buyDTOFuel))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void Buy_when_request_fails() {
        when(cardService.buy(any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(patch(CARD.concat(BUY))
                .content(objectMapper.writeValueAsString(buyDTOFuel))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }

    @SneakyThrows
    @Test
    void Buy_when_request_body_doesnt_exist() {
        this.mockMvc.perform(patch(CARD.concat(BUY))
                .content(objectMapper.writeValueAsString(Strings.EMPTY))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @SneakyThrows
    @Test
    void UpdateBalance_when_request_is_ok() {
        this.mockMvc.perform(patch(CARD.concat(BALANCE))
                .content(objectMapper.writeValueAsString(getNewCard()))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void UpdateBalance_when_request_fails() {
        when(cardService.updateBalance(any(), any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(patch(CARD.concat(BALANCE))
                .content(objectMapper.writeValueAsString(getNewCard()))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }
}
