package br.com.alelo.consumer.consumerpat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.controller.dto.BuyDTO;
import br.com.alelo.consumer.consumerpat.controller.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@AutoConfigureMockMvc
@SpringBootTest(classes = ConsumerTestApplication.class)
public class ConsumerControllerTest {
	
	private static Random random = new Random();
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ConsumerRepository consumerRespository;
	
	@Autowired
	private CardRepository cardRespository;

	@Test
	@Transactional
	void createValidConsumer() throws Exception, Throwable {
		int databaseSizeBeforeCreate = this.consumerRespository.findAll().size();
		Consumer consumer = createEntity();

		mockMvc.perform(
				post("/consumer").contentType("application/json").content(objectMapper.writeValueAsString(consumer)))
				.andExpect(status().isOk());
		
		List<Consumer> consumers = this.consumerRespository.findAll();
		assertThat(consumers).hasSize(databaseSizeBeforeCreate + 1);
		Consumer created = consumers.get(0);
		assertThat(created.getNumber()).isEqualTo(Integer.MAX_VALUE);

	}
	
	@Test
	@Transactional
	void updateValidConsumer() throws Exception, Throwable {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		consumer.setNumber(Integer.MIN_VALUE);

		mockMvc.perform(
				put("/consumer/{id}", consumer.getId()).contentType("application/json").content(objectMapper.writeValueAsString(consumer)))
				.andExpect(status().isOk());
		
		Consumer updated = this.consumerRespository.findById(consumer.getId()).get();
		assertThat(updated.getNumber()).isEqualTo(Integer.MIN_VALUE);
		

	}
	
	@Test
	@Transactional
	void setCardBalance() throws Exception, Exception {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		CardBalanceDTO cardBalance = new CardBalanceDTO(1000.0);
		
		mockMvc.perform(
				put("/consumer/setcardbalance/{number}", consumer.getCards().get(0).getNumber()).contentType("application/json").content(objectMapper.writeValueAsString(cardBalance)))
				.andExpect(status().isOk());
		
		Card card = this.cardRespository.findOneByNumber(consumer.getCards().get(0).getNumber());
		
		assertThat(card.getBalance()).isEqualTo(2000.0);
			
	}
	
	@Test
	@Transactional
	void buyFood() throws Exception, Exception {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		BuyDTO buy = new BuyDTO(1, "Restaurante Pé Sujo", "Prato Feito",  100.0);
		
		mockMvc.perform(
				put("/consumer/buy/{number}", consumer.getCards().get(0).getNumber()).contentType("application/json").content(objectMapper.writeValueAsString(buy)))
				.andExpect(status().isOk());
		
		Card card = this.cardRespository.findOneByNumber(consumer.getCards().get(0).getNumber());
		
		assertThat(card.getBalance()).isEqualTo(910.0);
			
	}
	
	@Test
	@Transactional
	void buyDrug() throws Exception, Exception {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		BuyDTO buy = new BuyDTO(2, "Droga Mais", "Uma Pírula da Cor do Céu",  100.0);
		
		mockMvc.perform(
				put("/consumer/buy/{number}", consumer.getCards().get(1).getNumber()).contentType("application/json").content(objectMapper.writeValueAsString(buy)))
				.andExpect(status().isOk());
		
		Card card = this.cardRespository.findOneByNumber(consumer.getCards().get(1).getNumber());
		
		assertThat(card.getBalance()).isEqualTo(900.0);
			
	}
	
	@Test
	@Transactional
	void buyFuel() throws Exception, Exception {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		BuyDTO buy = new BuyDTO(3, "Posto Enche Mais", "10% Gas + 90% Ar",  100.0);
		
		mockMvc.perform(
				put("/consumer/buy/{number}", consumer.getCards().get(2).getNumber()).contentType("application/json").content(objectMapper.writeValueAsString(buy)))
				.andExpect(status().isOk());
		
		Card card = this.cardRespository.findOneByNumber(consumer.getCards().get(2).getNumber());
		
		assertThat(card.getBalance()).isEqualTo(865.0);
			
	}
	
	@Test
	@Transactional
	void errorBuyFuelInFoodEstashiment() throws Exception, Exception {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		BuyDTO buy = new BuyDTO(1, "Restaurante Pé Sujo", "10% Gas + 90% Ar",  100.0);
		
		mockMvc.perform(
				put("/consumer/buy/{number}", consumer.getCards().get(2).getNumber()).contentType("application/json").content(objectMapper.writeValueAsString(buy)))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
	
	@Test
	@Transactional
	void errorCardNotFound() throws Exception, Exception {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		BuyDTO buy = new BuyDTO(1, "Restaurante Pé Sujo", "10% Gas + 90% Ar",  100.0);
		
		mockMvc.perform(
				put("/consumer/buy/{number}", consumer.getCards().get(2).getNumber() + 1).contentType("application/json").content(objectMapper.writeValueAsString(buy)))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}
	

	@Test
	@Transactional
	void listConsumers() throws Exception, Throwable {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		

		mockMvc.perform(
				get("/consumer"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.[*].id").value(hasItem(consumer.getId().intValue())));

	}
	
	

	private Consumer createEntity() {
		Consumer consumer = new Consumer();
		consumer.setBirthDate(new Date());
		consumer.setCity("New York");
		consumer.setCountry("USA");
		consumer.setDocumentNumber(Integer.MAX_VALUE);
		consumer.setEmail("email@email.com");
		consumer.setMobilePhoneNumber(Integer.MAX_VALUE);
		consumer.setName("Biden Trump");
		consumer.setNumber(Integer.MAX_VALUE);
		consumer.setPhoneNumber(Integer.MAX_VALUE);
		consumer.setPortalCode(Integer.MAX_VALUE);
		consumer.setResidencePhoneNumber(Integer.MAX_VALUE);
		consumer.setStreet("13th Avenue");
		consumer.getCards().add(createCard(CardType.FOOD, consumer));
		consumer.getCards().add(createCard(CardType.DRUG, consumer));		
		consumer.getCards().add(createCard(CardType.FUEL, consumer));
		return consumer;
	}
	
	private Card createCard(CardType type, Consumer consumer) {
		Card card = new Card();
		card.setBalance(1000.0);
		card.setConsumer(consumer);
		card.setNumber(random.nextInt());
		card.setType(type);
		return card;
	}

}
