package br.com.alelo.consumer.consumerpat.controller;

import static br.com.alelo.consumer.consumerpat.domain.enums.CardType.FOOD;
import static br.com.alelo.consumer.consumerpat.helper.PayloadMocks.cardPayload;
import static br.com.alelo.consumer.consumerpat.helper.PayloadMocks.consumerPayload;
import static br.com.alelo.consumer.consumerpat.helper.ResponseMocks.cardResponse;
import static br.com.alelo.consumer.consumerpat.helper.ResponseMocks.consumerResponse;
import static br.com.alelo.consumer.consumerpat.helper.UtilMocks.page;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.exception.CardAlreadyExistsException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@WebMvcTest(ConsumerController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ConsumerControllerTests {
	
	@MockBean
	private ConsumerService consumerService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static final String BASE_URL = "/consumers/";
	
	private static final String CARD_NUMBER = "1234123412341234";
	
	@Test
	void shouldListAllConsumersWithResponse200() throws Exception {
		final var page = PageRequest.of(0, 50);
		
		when(consumerService.listAllConsumers(page))
			.thenReturn(page(page, consumerResponse(cardResponse(CARD_NUMBER, FOOD))));
		
		mockMvc.perform(get(BASE_URL + "?page=0&size=50")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}
	
	@Test
	void shouldCreateConsumerWithResponse201() throws Exception {
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(
						consumerPayload(cardPayload(CARD_NUMBER, FOOD)))))
			   .andExpect(status().isCreated());
	}
	
	@Test
	void shouldFailToCreateConsumerWithUsedCardNumberWithResponse409() throws Exception {
		final var payload = consumerPayload(cardPayload(CARD_NUMBER, FOOD));
		
		doThrow(new CardAlreadyExistsException(CARD_NUMBER)).when(consumerService).createConsumer(payload);
		
		mockMvc.perform(post(BASE_URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(payload)))
			   .andExpect(status().isConflict());
	}
	
	@Test
	void shouldUpdateConsumerWithResponse202() throws Exception {
		final var payload = consumerPayload(cardPayload(CARD_NUMBER, FOOD));
		
		mockMvc.perform(put(BASE_URL + 1)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(payload)))
			   .andExpect(status().isAccepted());
	}
	
	@Test
	void shouldFailToUpdateUnexistentConsumerWithResponse404() throws Exception {
		final var payload = consumerPayload(cardPayload(CARD_NUMBER, FOOD));
		
		doThrow(new ConsumerNotFoundException(1)).when(consumerService).updateConsumer(1, payload);
		
		mockMvc.perform(put(BASE_URL + 1)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(payload)))
			   .andExpect(status().isNotFound());
	}
	
	@Test
	void shouldFailToUpdatConsumerWithAlreadyUsedCardAndResponse409() throws Exception {
		final var payload = consumerPayload(cardPayload(CARD_NUMBER, FOOD));
		
		doThrow(new CardAlreadyExistsException(CARD_NUMBER)).when(consumerService).updateConsumer(1, payload);
		
		mockMvc.perform(put(BASE_URL + 1)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(payload)))
			   .andExpect(status().isConflict());
	}
}
