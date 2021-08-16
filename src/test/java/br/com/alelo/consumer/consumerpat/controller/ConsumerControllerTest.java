package br.com.alelo.consumer.consumerpat.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.model.dto.card.CardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.card.CategoryDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.AddressDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ContactDTO;
import br.com.alelo.consumer.consumerpat.service.consumer.ConsumerService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ConsumerControllerTest {	
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ConsumerService consumerService;
	
	@Test
	void createConsumer() throws Exception {

		ConsumerDTO consumerDTO = createConsumerDTO();
		consumerDTO.getCards().get(0).setNumber("123456784");
		
		MockHttpServletRequestBuilder requestConsumer = MockMvcRequestBuilders.post("/consumers")
				.contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consumerDTO));
		mockMvc.perform(requestConsumer).andExpect(status().is2xxSuccessful()).andReturn();

	}
	
	
	@Test
	void getConsumers() throws Exception {

		ConsumerDTO consumerDTO = createConsumerDTO();		
		consumerDTO.getCards().get(0).setNumber("123456786");
		
		MockHttpServletRequestBuilder requestConsumer = MockMvcRequestBuilders.post("/consumers")
				.contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consumerDTO));
		mockMvc.perform(requestConsumer).andExpect(status().is2xxSuccessful()).andReturn();
		
		consumerDTO.getCards().get(0).setNumber("123456787");
		MockHttpServletRequestBuilder request2Consumer = MockMvcRequestBuilders.post("/consumers")
				.contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consumerDTO));
		mockMvc.perform(request2Consumer).andExpect(status().is2xxSuccessful()).andReturn();
		
		MockHttpServletRequestBuilder requestGetConsumer = MockMvcRequestBuilders.get("/consumers")
				.contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consumerDTO));
		mockMvc.perform(requestGetConsumer).andExpect(status().is2xxSuccessful()).andReturn();

	}
	

	private ConsumerDTO createConsumerDTO() {
		AddressDTO address = AddressDTO.builder().city("Brasilia").country("Brasíl").number(7).portalCode(700000)
				.street("710 Norte").build();

		CardDTO card = CardDTO.builder().balance(11.0).number("123456789").category(CategoryDTO.builder().id(1).build())
				.build();

		ContactDTO contact = ContactDTO.builder().email("fulano@gmail.com").mobilePhoneNumber(986748976).build();

		List<CardDTO> cardDTOs = new ArrayList<>();
		cardDTOs.add(card);
		ConsumerDTO consumerDTO = ConsumerDTO.builder().name("Fulano").documentNumber(123456).birthDate(LocalDate.now())
				.address(address).contact(contact).cards(cardDTOs).birthDate(LocalDate.of(1985, 03, 12)).build();
		
		return consumerDTO;
	}	
}
