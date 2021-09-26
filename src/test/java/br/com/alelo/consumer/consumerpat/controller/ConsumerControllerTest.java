package br.com.alelo.consumer.consumerpat.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alelo.consumer.consumerpat.dto.ConsumerContactRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequestDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ConsumerControllerTest {

	@Autowired 
	private MockMvc mockMvc;
	
	@Autowired 
	private ObjectMapper mapper;


	@MockBean
	private ConsumerController consumerController;

	@Test
	public void testCreateConsumer() throws Exception {

		ConsumerContactRequestDTO consumerContactRequestDTO = new ConsumerContactRequestDTO();
		consumerContactRequestDTO.setPhoneType(1);
		consumerContactRequestDTO.setDddNumber(11);
		consumerContactRequestDTO.setPhoneNumber(33333445L);
		
		ConsumerRequestDTO consumer = new ConsumerRequestDTO();
		consumer.setName("TESTE");
		consumer.setDocumentNumber(1L);
		consumer.setBirthDate("01/01/1983");
		consumer.setContact(consumerContactRequestDTO);
		
		
		String json = mapper.writeValueAsString(consumer);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/consumer")
				  .content(json)
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated());
		
	}
}
