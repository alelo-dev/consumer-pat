package br.com.alelo.consumer.consumerpat.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConsumerServiceTest {
	
	@MockBean
	private ConsumerRepository repository;
	@InjectMocks
	private ConsumerService service;
	
	@Mock
	Consumer consumer;
	@Mock
	Extract extract;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	void setup() {
		consumer.setCity("João Pessoa");
		consumer.setCountry("Brasil");
		consumer.setDocumentNumber(42193219);
		consumer.setDrugstoreCardBalance(100.0);
		consumer.setDrugstoreCardNumber(1);
		consumer.setEmail("caio@hot.com");
		consumer.setFoodCardBalance(150.0);
		consumer.setFoodCardNumber(2);
		consumer.setFuelCardBalance(160.0);
		consumer.setFuelCardNumber(3);
		consumer.setMobilePhoneNumber(839392329);
		consumer.setName("Test");
		consumer.setNumber(127);
		consumer.setPhoneNumber(1321321322);
		consumer.setPortalCode(58280000);
		consumer.setResidencePhoneNumber(1321321322);
		consumer.setStreet("Rua Campo");
		consumer.setId(1);
		extract.setCardNumber(1321322);
		extract.setDateBuy(new Date());
		extract.setEstablishmentName("Loja Test");
		extract.setEstablishmentNameId(12);
		extract.setId(12);
		extract.setProductDescription("Loja feita apenas para testes");
		extract.setValue(15.0);
	}
	@Test
	@DisplayName("Teste de listagem todos os consumidores")
	void listAllConsumerTest() {
		Mockito.when(repository.getAllConsumersList()).thenReturn(new ArrayList<Consumer>());
		List<Consumer> list = service.listAllConsumer();
		assertNotNull(list);
	}
	
	@Test
	@DisplayName("Teste para criar cliente novo")
	void createConsumerTest() {
		service.createConsumer(consumer);
		assertNotNull(repository.getAllConsumersList());
	}
	@Test
	@DisplayName("Atualizando saldo sem sucesso")
	void setCardBalanceFailTest() {
		Mockito.when(repository.findByDrugstoreNumber(consumer.getDrugstoreCardNumber())).thenReturn(consumer);
		String response = service.setBalance(consumer,2);
		assertEquals("Cartão não existe", response);
	}


}
