package br.com.alelo.consumer.consumerpat.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.controller.CardController;
import br.com.alelo.consumer.consumerpat.model.Balance;
import br.com.alelo.consumer.consumerpat.model.dto.card.CardDTO;
import br.com.alelo.consumer.consumerpat.model.dto.card.CategoryDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.AddressDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.dto.consumer.ContactDTO;
import br.com.alelo.consumer.consumerpat.model.purchase.Purchase;
import br.com.alelo.consumer.consumerpat.service.card.CardService;

@SpringBootTest
@AutoConfigureMockMvc
class CardControllerTest {
	
		
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	CardService service;	
	
	@InjectMocks
	CardController cardController;
	
	@BeforeEach
    void initService() {
        MockitoAnnotations.openMocks(this);        
    }
	
	@Test	
	void balances() throws Exception {
		
		ConsumerDTO consumerDTO = createConsumerDTO();
		
		Balance balance = Balance.builder()
				.cardNumber("123456789")
				.category(1)
				.value(10.0)
				.build();	
		
		MockHttpServletRequestBuilder requestConsumer = MockMvcRequestBuilders.post("/consumers")
				.contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consumerDTO));
		mockMvc.perform(requestConsumer).andExpect(status().is2xxSuccessful()).andReturn();		
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/cards/balances").contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON).content(objectMapper.writeValueAsString(balance));				
		mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andReturn();
	}

	@Test
	void purchase() throws Exception {		
		
		ConsumerDTO consumerDTO = createConsumerDTO();
		consumerDTO.getCards().get(0).setNumber("123456780");
		
		Purchase purchase = Purchase.builder()
				.cardNumber("123456780")
				.establishmentType(1)
				.establishmentName("Qualquer")				
				.productDescription("Doce")
				.value(10.0)
				.build();
		
		MockHttpServletRequestBuilder requestConsumer = MockMvcRequestBuilders.post("/consumers")
				.contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(consumerDTO));
		mockMvc.perform(requestConsumer).andExpect(status().is2xxSuccessful()).andReturn();		
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/cards/purchases").contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON).content(objectMapper.writeValueAsString(purchase));				
		mockMvc.perform(request).andExpect(status().is2xxSuccessful()).andReturn();
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
