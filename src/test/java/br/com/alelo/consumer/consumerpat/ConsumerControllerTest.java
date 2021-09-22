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

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@AutoConfigureMockMvc
@SpringBootTest(classes = ConsumerTestApplication.class)
public class ConsumerControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ConsumerRepository consumerRespository;

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
	void updateValidConsumer() throws Exception, Throwable {
		Consumer consumer = createEntity();
		this.consumerRespository.save(consumer);
		
		consumer.setNumber(Integer.MIN_VALUE);
		consumer.setDrugstoreCardBalance(Double.MIN_VALUE);
		consumer.setFuelCardBalance(Double.MIN_VALUE);
		consumer.setFoodCardBalance(Double.MIN_VALUE);

		mockMvc.perform(
				put("/consumer").contentType("application/json").content(objectMapper.writeValueAsString(consumer)))
				.andExpect(status().isOk());
		
		Consumer updated = this.consumerRespository.findById(consumer.getId()).get();
		assertThat(updated.getNumber()).isEqualTo(Integer.MIN_VALUE);
		
		//NOT ALLOW TO CHANGE BALANCES BY METHOD PUT
		assertThat(updated.getDrugstoreCardBalance()).isEqualTo(Double.MAX_VALUE);
		assertThat(updated.getFoodCardBalance()).isEqualTo(Double.MAX_VALUE);
		assertThat(updated.getFuelCardBalance()).isEqualTo(Double.MAX_VALUE);
		this.consumerRespository.delete(updated);

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
		consumer.setDrugstoreCardBalance(Double.MAX_VALUE);
		consumer.setDrugstoreNumber(Integer.MAX_VALUE);
		consumer.setEmail("email@email.com");
		consumer.setFoodCardBalance(Double.MAX_VALUE);
		consumer.setFoodCardNumber(Integer.MAX_VALUE);
		consumer.setFuelCardBalance(Double.MAX_VALUE);
		consumer.setFuelCardNumber(Integer.MAX_VALUE);
		consumer.setMobilePhoneNumber(Integer.MAX_VALUE);
		consumer.setName("Biden Trump");
		consumer.setNumber(Integer.MAX_VALUE);
		consumer.setPhoneNumber(Integer.MAX_VALUE);
		consumer.setPortalCode(Integer.MAX_VALUE);
		consumer.setResidencePhoneNumber(Integer.MAX_VALUE);
		consumer.setStreet("13th Avenue");
		return consumer;
	}

}
