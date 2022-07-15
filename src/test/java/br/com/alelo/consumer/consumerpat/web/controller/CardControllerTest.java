package br.com.alelo.consumer.consumerpat.web.controller;

import br.com.alelo.consumer.consumerpat.exception.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.model.entity.Card;
import br.com.alelo.consumer.consumerpat.model.enums.EstablishmentType;
import br.com.alelo.consumer.consumerpat.service.CardService;
import br.com.alelo.consumer.consumerpat.web.vo.card.UpdateCardFormVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static br.com.alelo.consumer.consumerpat.fixture.ConsumerPatFixture.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CardControllerTest {

    private static final String NOT_FOUND_MESSAGE = "Card not found!";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CardService cardService;

    @Test
    void findByCardNumber_WithInvalidNumber_ShouldReturnCardNotFound() throws Exception {
        given(cardService.findByCardNumber(CARD_INVALID_NUMBER))
                .willThrow(new ResourceNotFoundException(NOT_FOUND_MESSAGE));

        mockMvc.perform(get("/v1/card/{id}", CARD_INVALID_NUMBER)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors", hasSize(0)))
            .andExpect(jsonPath("$.details", is(NOT_FOUND_MESSAGE)));
    }

    @Test
    void findByCardNumber_WithValidNumber_ShouldReturnCorrespondingCard() throws Exception {
        Card card = buildCard(EstablishmentType.FOOD, null);

        given(cardService.findByCardNumber(CARD_NUMBER)).willReturn(card);

        mockMvc.perform(get("/v1/card/{cardNumber}", card.getNumber())
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(card.getId().intValue())))
            .andExpect(jsonPath("$.number", is(card.getNumber())))
            .andExpect(jsonPath("$.balance", is(card.getBalance().doubleValue())))
            .andExpect(jsonPath("$.type", is(card.getType().name())));
    }

    @Test
    void updateCardBalance_WithInvalidNumber_ShouldReturnCardNotFound() throws Exception {
        given(cardService.updateCardBalance(eq(CARD_INVALID_NUMBER), any(UpdateCardFormVO.class)))
            .willThrow(new ResourceNotFoundException(NOT_FOUND_MESSAGE));

        UpdateCardFormVO body = new UpdateCardFormVO(BigDecimal.valueOf(100.00));

        mockMvc.perform(put("/v1/card/{cardNumber}/balance", CARD_INVALID_NUMBER)
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isArray())
            .andExpect(jsonPath("$.errors", hasSize(0)))
            .andExpect(jsonPath("$.details", is(NOT_FOUND_MESSAGE)));
    }

    @Test
    void updateCardBalance_WithNullValue_ShouldReturnInvalidValueMessage() throws Exception {
        UpdateCardFormVO body = new UpdateCardFormVO(null);

        mockMvc.perform(put("/v1/card/{cardNumber}/balance", CARD_NUMBER)
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isMap())
            .andExpect(jsonPath("$.errors", aMapWithSize(1)))
            .andExpect(jsonPath("$.errors", hasEntry("value", "Value cannot be null")))
            .andExpect(jsonPath("$.details", is("uri=/v1/card/" + CARD_NUMBER + "/balance")));
    }

    @Test
    void updateCardBalance_WithValueLessThanZero_ShouldReturnInvalidValueMessage() throws Exception {
        UpdateCardFormVO body = new UpdateCardFormVO(BigDecimal.valueOf(100.00).negate());

        mockMvc.perform(put("/v1/card/{cardNumber}/balance", CARD_NUMBER)
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp", is(notNullValue())))
            .andExpect(jsonPath("$.errors").exists())
            .andExpect(jsonPath("$.errors").isMap())
            .andExpect(jsonPath("$.errors", aMapWithSize(1)))
            .andExpect(jsonPath("$.errors", hasEntry("value", "The smallest amount allowed for the value is zero")))
            .andExpect(jsonPath("$.details", is("uri=/v1/card/" + CARD_NUMBER + "/balance")));
    }

    @Test
    void updateCardBalance_WithValueGreaterThanZero_ShouldAddValueToCardBalance() throws Exception {
        Card card = buildCard(EstablishmentType.FOOD, null);

        given(cardService.updateCardBalance(eq(card.getNumber()), any(UpdateCardFormVO.class))).willReturn(card);

        UpdateCardFormVO body = new UpdateCardFormVO(BigDecimal.valueOf(100.00));

        mockMvc.perform(put("/v1/card/{cardNumber}/balance", card.getNumber())
            .content(objectMapper.writeValueAsString(body))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(card.getId().intValue())))
            .andExpect(jsonPath("$.number", is(card.getNumber())))
            .andExpect(jsonPath("$.balance", is(card.getBalance().doubleValue())))
            .andExpect(jsonPath("$.type", is(card.getType().name())));
    }

}