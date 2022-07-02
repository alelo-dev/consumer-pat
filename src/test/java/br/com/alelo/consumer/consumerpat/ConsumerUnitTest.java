package br.com.alelo.consumer.consumerpat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ConsumerUnitTest {
	@MockBean
	ConsumerRepository consumerRepository;
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	private void setup(){
		consumerRepository.deleteAll();
	}

	Consumer consumer1 = new Consumer("name", 1, new Date(), 12345678, 123456789, 123456789, "email@email.com", "street", 1, "city", "country", 12345678, 123, 0, 123, 0, 123, 0);
	Consumer consumer2 = new Consumer("name2", 2, new Date(), 12345672, 123456782, 123456782, "email2@email.com", "street2", 2, "city2", "country2", 12345672, 1232, 0, 1232, 0, 1232, 0);

	@Test
	public void assertListAllConsumersIsEmpty() throws Exception{
		List<Consumer> consumerEmptyList = new ArrayList<>();

		Mockito.when(consumerRepository.findAll()).thenReturn(consumerEmptyList);

		mockMvc.perform(MockMvcRequestBuilders.get("/consumer/consumerList")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void assertListAllConsumers() throws Exception{
		List<Consumer> consumersList = new ArrayList<>(Arrays.asList(consumer1, consumer2));

		consumerRepository.saveAll(Arrays.asList(consumer1, consumer2));

		Mockito.when(consumerRepository.findAll()).thenReturn(consumersList);

		mockMvc.perform(MockMvcRequestBuilders.get("/consumer/consumerList")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void createConsumerSuccessfully() throws Exception{
		consumerRepository.save(consumer1);

		Mockito.when(consumerRepository.save(consumer1)).thenReturn(consumer1);

		mockMvc.perform(MockMvcRequestBuilders.post("/consumer/createConsumer")
						.content(this.objectMapper.writeValueAsString(consumer1))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	public void updateConsumerSuccessfully() throws Exception{
		consumerRepository.save(consumer1);

		Mockito.when(consumerRepository.findById(consumer1.getId())).thenReturn(Optional.of(consumer1));

		Mockito.doNothing().when(consumerRepository).update(consumer1.getId(), "name2", 12, new Date(), 12345672, 123456782, 123456782, "email2@email.com", "street2", 12, "city2", "country2", 12345672, 1232, 123, 1232);

		mockMvc.perform(MockMvcRequestBuilders.put("/consumer/updateConsumer")
						.content(this.objectMapper.writeValueAsString(consumer1))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
