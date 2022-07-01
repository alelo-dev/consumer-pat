package br.com.alelo.consumer.consumerpat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConsumerControllerTest {
	@MockBean
	private ConsumerRepository repoConsumer;
	
	@MockBean
	private ConsumerService service;
	
	@InjectMocks
	private ConsumerController controller;
	
	@Mock
	Consumer consumer;
	
	@Mock
	List<Consumer> consumerList = new ArrayList<Consumer>();
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		consumer.setBirthDate(new Date());
		consumer.setCity("SP");
		consumer.setCountry("Brasil");
		consumer.setDocumentNumber(12);
		consumer.setDrugstoreCardBalance(1.0);
		consumer.setDrugstoreNumber(1);
		consumer.setEmail("teste@test");
		consumer.setFoodCardBalance(1.0);
		consumer.setFoodCardNumber(2);
		consumer.setFuelCardBalance(1.0);
		consumer.setFuelCardNumber(3);
		consumer.setMobilePhoneNumber(12);
		consumer.setName("Test");
		consumer.setNumber(1);
		consumer.setPhoneNumber(12);
		consumer.setPortalCode(12);
		consumer.setResidencePhoneNumber(12);
		consumer.setStreet("Rua test");
		consumerList.add(consumer);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listAllConsumersTest() {
		Mockito.when(repoConsumer.getAllConsumersList()).thenReturn(consumerList);
		Mockito.when(service.listAllConsumers()).thenReturn(consumerList);
		List<Consumer> listTest = controller.listAllConsumers();
		assertEquals(consumerList, listTest);
	}
	
	@Test
	public void createConsumerTest() {
		Mockito.when(service.createConsumer(consumer)).thenReturn("Consumer criado com sucesso");
		ResponseEntity<Object> response = controller.createConsumer(consumer);
		assertEquals(new ResponseEntity<Object>("Consumer criado com sucesso", HttpStatus.OK), response);
	}
	
	@Test
	public void updateConsumerTest() throws Exception {
		Mockito.when(service.updateConsumer(consumer)).thenReturn("Consumer atualizado com sucesso.");
		ResponseEntity<Object> response = controller.updateConsumer(consumer);
		assertEquals(new ResponseEntity<Object>("Consumer atualizado com sucesso.", HttpStatus.OK), response);
	}
	
	@Test
	public void setCardBalanceTest() throws Exception {
		Mockito.when(service.setBalance(1,2)).thenReturn("Saldo do cartão atualizado com sucesso.");
		ResponseEntity<Object> response = controller.setBalance(1,2);
		assertEquals(new ResponseEntity<Object>("Saldo do cartão atualizado com sucesso.", HttpStatus.OK), response);
	}
}
