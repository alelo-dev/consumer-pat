package br.com.alelo.consumer.consumerpat.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.alelo.consumer.consumerpat.dto.AddressRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.CardConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerContactRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerResponseDTO;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsumerServiceImplTest {
	
	
	@Autowired 
	private ConsumerServiceImpl consumerServiceImpl;

	@Test
	void test_create_consumer_success() throws Exception {
		
		ConsumerRequestDTO consumerRequestDTO = this.createConsumerRequest();
		ConsumerResponseDTO consumerResponse = consumerServiceImpl.createNewConsumer(consumerRequestDTO);
	
		assertNotNull(consumerResponse);
		
	}
	
	@Test
	void test_update_consumer_success() throws Exception {
		
		ConsumerRequestDTO consumerRequestDTO = this.createConsumerRequest();
		consumerServiceImpl.createNewConsumer(consumerRequestDTO);
		
		ConsumerRequestDTO consumerRequestUpdatDTO = new ConsumerRequestDTO();
		consumerRequestUpdatDTO.setId(1L);
		consumerRequestUpdatDTO.setName("ATUALIZADO");
		
		ConsumerResponseDTO consumerResponse = consumerServiceImpl.updateConsumer(1L, consumerRequestUpdatDTO);
	
		assertNotNull(consumerResponse);
		
	}
	
	
	private ConsumerRequestDTO createConsumerRequest() {
		
		ConsumerRequestDTO consumerRequestDTO = new ConsumerRequestDTO();
		consumerRequestDTO.setEmail("teste@teste.com.br");
		consumerRequestDTO.setName("teste");
		consumerRequestDTO.setDocumentNumber(11112222L);
		consumerRequestDTO.setBirthDate("01/01/1983");
		
		AddressRequestDTO addressRequestDTO = new AddressRequestDTO();
		addressRequestDTO.setAddressNumber(11);
		addressRequestDTO.setAddressStreet("RUA TESTE");
		addressRequestDTO.setCity("Sao Paulo");
		addressRequestDTO.setCountry("Brasil");
		addressRequestDTO.setPostalCode("22222-222");
		
		
		ConsumerContactRequestDTO contactRequestDTO = new ConsumerContactRequestDTO();
		contactRequestDTO.setDddNumber(11);
		contactRequestDTO.setPhoneNumber(new Random().nextLong());
		contactRequestDTO.setPhoneType(1);
		
		consumerRequestDTO.setAddress(addressRequestDTO);
		consumerRequestDTO.setContact(contactRequestDTO);
		
		CardConsumerRequestDTO cardConsumerRequestDTO = new CardConsumerRequestDTO();
		cardConsumerRequestDTO.setTypeCard(1L);
		cardConsumerRequestDTO.setCardNumber("1112222333344444");
		cardConsumerRequestDTO.setBalance(0.0);
		
		List<CardConsumerRequestDTO> list = new ArrayList();
		list.add(cardConsumerRequestDTO);
		
		consumerRequestDTO.setCards(list);
	
		return consumerRequestDTO;
		
	}
	

	@Test
	@Transactional
	void test_findall_consumer_success() throws Exception {
		
		ConsumerRequestDTO consumerRequestDTO = this.createConsumerRequest();
		consumerServiceImpl.createNewConsumer(consumerRequestDTO);
		
		List<ConsumerResponseDTO> consumerResponse = consumerServiceImpl.getAllConsumersList(0, 10, "name");
	
		assertEquals(consumerResponse.size(), 1);
		
	}

}
