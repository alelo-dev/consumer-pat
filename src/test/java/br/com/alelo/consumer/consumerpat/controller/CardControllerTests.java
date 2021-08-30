package br.com.alelo.consumer.consumerpat.controller;

import static br.com.alelo.consumer.consumerpat.domain.enums.CardType.FOOD;
import static br.com.alelo.consumer.consumerpat.domain.enums.CardType.FUEL;
import static br.com.alelo.consumer.consumerpat.helper.PayloadMocks.buyPayload;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeMismatchException;
import br.com.alelo.consumer.consumerpat.service.CardService;

@WebMvcTest(CardController.class)
@TestInstance(Lifecycle.PER_CLASS)
class CardControllerTests {
	
	@MockBean
	private CardService cardService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static final String BASE_URL = "/cards/";
	
	private static final String CARD_NUMBER = "1234123412341234";
	
	
	@Test
	void shouldSetBalanceWithResponse202() throws Exception {
		mockMvc.perform(patch(BASE_URL + CARD_NUMBER + "/balance")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("15.50"))
				.andExpect(status().isAccepted());
	}
	
	@Test
	void shouldFailToSetBalanceWhenCardDoesntExistWithResponse404() throws Exception {
		doThrow(new CardNotFoundException(CARD_NUMBER)).when(cardService).setBalance(CARD_NUMBER, new BigDecimal("15.50"));
	
		mockMvc.perform(patch(BASE_URL + CARD_NUMBER + "/balance")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("15.50"))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldBuyItemsUsingCardWithResponse202() throws Exception {
		mockMvc.perform(patch(BASE_URL + CARD_NUMBER + "/buy")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper
						.writeValueAsString(buyPayload(CARD_NUMBER, FOOD))))
				.andExpect(status().isAccepted());
	}
	
	@Test
	void shouldFailToBuyItemsUsingAnUnexistentCardWithResponse404() throws Exception {
		final var payload = buyPayload(CARD_NUMBER, FOOD);
		
		doThrow(new CardNotFoundException(CARD_NUMBER)).when(cardService).buy(CARD_NUMBER, payload);
		
		mockMvc.perform(patch(BASE_URL + CARD_NUMBER + "/buy")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(payload)))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldFailToBuyItemsUsingCardInInvalidEstablishmentWithResponse409() throws Exception {
		final var payload = buyPayload(CARD_NUMBER, FOOD);
		
		doThrow(new CardTypeMismatchException(FOOD, FUEL)).when(cardService).buy(CARD_NUMBER, payload);
		
		mockMvc.perform(patch(BASE_URL + CARD_NUMBER + "/buy")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(payload)))
				.andExpect(status().isConflict());
	}

}
