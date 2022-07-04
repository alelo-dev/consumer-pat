package br.com.alelo.consumer.consumerpat.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ConsumerControllerTest {
	@MockBean
	private ConsumerRepository repository;

	@MockBean
	private ConsumerService service;

	@InjectMocks
	private ConsumerController controller;

	@Mock
	Consumer consumer;
	
	@Mock
	Extract extract;
	
	@Mock
	List<Extract> extractList;
	
	@Mock
	List<Consumer> consumerList = new ArrayList<Consumer>();

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		consumer.setCity("João Pessoa");
		consumer.setCountry("Brasil");
		consumer.setDocumentNumber(42193219);
		consumer.setDrugstoreCardBalance(100.0);
		consumer.setDrugstoreCardNumber(1321321);
		consumer.setEmail("caio@hot.com");
		consumer.setFoodCardBalance(150.0);
		consumer.setFoodCardNumber(1321322);
		consumer.setFuelCardBalance(160.0);
		consumer.setFuelCardNumber(1321323);
		consumer.setMobilePhoneNumber(839392329);
		consumer.setName("Test");
		consumer.setNumber(127);
		consumer.setPhoneNumber(1321321322);
		consumer.setPortalCode(58280000);
		consumer.setResidencePhoneNumber(1321321322);
		consumer.setStreet("Rua Campo");
		extract.setCardNumber(1321322);
		extract.setDateBuy(new Date());
		extract.setEstablishmentName("Loja Test");
		extract.setEstablishmentNameId(12);
		extract.setId(12);
		extract.setProductDescription("Loja feita apenas para testes");
		extract.setValue(15.0);
		extractList.add(extract);
		consumerList.add(consumer);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	@DisplayName("Teste de listagem todos os consumidores")
	void listAllConsumerTest() {
		Mockito.when(repository.getAllConsumersList()).thenReturn(consumerList);
		Mockito.when(service.listAllConsumer()).thenReturn(consumerList);
		List<Consumer> list = controller.listAllConsumers();
		assertNotNull(list);
	}
	
	@Test
	@DisplayName("Teste para criar cliente novo")
	void createConsumerTest() {
		ResponseEntity<String> response = controller.createConsumer(consumer);
		assertEquals(new ResponseEntity<String>("Cliente cadastrado com sucesso", HttpStatus.OK), response);
	}
	
	@Test
	@DisplayName("Atualizar o consumidor")
	void updateConsumerTest() {
		Mockito.when(service.updateConsumer(consumer)).thenReturn("Alteração feita com sucesso");
		ResponseEntity<String> response = controller.updateConsumer(consumer);
		assertEquals(new ResponseEntity<String>("Alteração feita com sucesso", HttpStatus.OK), response);
	}
	
	@Test
	@DisplayName("Atualizando saldo")
	void setCardBalanceTest() {
		Mockito.when(service.setBalance(consumer,2)).thenReturn("Saldo de Farmacia atualizado");
		ResponseEntity<String> response = controller.setBalance(consumer,2);
		assertEquals(new ResponseEntity<String>("Saldo de Farmacia atualizado", HttpStatus.OK), response);
	}
	
	@Test
	@DisplayName("Teste de compra")
	void buyTest() {
		Mockito.when(service.buy(extract,consumer,2, 1)).thenReturn("Compra realizada com sucesso!");
		ResponseEntity<String> response = controller.buy(extract,consumer,2, 1);
		assertEquals(new ResponseEntity<String>("Compra realizada com sucesso!", HttpStatus.OK), response);
	}
}
