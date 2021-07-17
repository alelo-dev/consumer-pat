package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.RequestBuyDTO;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.service.IExtractService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static br.com.alelo.consumer.consumerpat.enums.CardType.FOOD;
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
    private static final String BALANCE = "/balance";
    private static final String PATH_VAR_CARD_NUMBER = "/{setcardbalance}";
    private static final String PATH_VAR_VALUE = "/{value}";

    private static final Long CARD_NUMBER = 1L;
    private static final BigDecimal VALUE_TEN = BigDecimal.TEN;

    @MockBean
    private ICardService service;

    @MockBean
    private IExtractService extractService;

    private RequestBuyDTO dtoFood;

    @BeforeEach
    public void setup() {
        dtoFood = RequestBuyDTO.builder()
                .establishmentType(FOOD)
                .cardNumber(CARD_NUMBER)
                .establishmentName(Strings.EMPTY)
                .value(BigDecimal.TEN)
                .build();
    }

    @SneakyThrows
    @Test
    void updateBalance_whenPutRequestIsOk_thenReturnIsOk() {
        this.mockMvc.perform(patch(CARD.concat(BALANCE).concat(PATH_VAR_CARD_NUMBER).concat(PATH_VAR_VALUE),
                    CARD_NUMBER, VALUE_TEN)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void updateBalance_whenPutRequestFailedService_thenReturnBadRequest() {
        when(service.updateBalance(any(), any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(patch(CARD.concat(BALANCE).concat(PATH_VAR_CARD_NUMBER).concat(PATH_VAR_VALUE),
                    CARD_NUMBER, VALUE_TEN)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }

    @SneakyThrows
    @Test
    void updateBalance_whenPutRequestPathVarNotExists_thenReturnNotFound() {
        this.mockMvc.perform(patch(CARD.concat(BALANCE).concat(PATH_VAR_CARD_NUMBER), CARD_NUMBER)
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @SneakyThrows
    @Test
    void buy_whenGetRequestIsOk_thenReturnIsOk() {
        this.mockMvc.perform(patch(CARD.concat(BUY))
                .content(objectMapper.writeValueAsString(dtoFood))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> assertEquals(APPLICATION_JSON_VALUE, mvcResult.getRequest().getContentType()));
    }

    @SneakyThrows
    @Test
    void buy_whenGetRequestFailedService_thenReturnResponseStatusException() {
        when(service.buy(any())).thenThrow(ResponseStatusException.class);
        this.mockMvc.perform(patch(CARD.concat(BUY))
                .content(objectMapper.writeValueAsString(dtoFood))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(mvcResult -> assertTrue(mvcResult.getResolvedException() instanceof ResponseStatusException));
    }

    @SneakyThrows
    @Test
    void buy_whenGetRequestBodyNotExists_thenBadRequest() {
        this.mockMvc.perform(patch(CARD.concat(BUY))
                .content(objectMapper.writeValueAsString(Strings.EMPTY))
                .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}
