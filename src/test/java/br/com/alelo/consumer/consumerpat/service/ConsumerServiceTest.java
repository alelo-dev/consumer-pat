package br.com.alelo.consumer.consumerpat.service;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConsumerServiceTest {

	@MockBean
	private ConsumerRepository repo;
	@InjectMocks
	private ConsumerService service;

	@Mock
	Consumer consumer;

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
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void listAllConsumersTest() {
		Mockito.when(repo.getAllConsumersList()).thenReturn(new ArrayList<Consumer>());
		List<Consumer> response = service.listAllConsumers();
		assertEquals(new ArrayList<Consumer>(), response);
	}

	@Test
	public void createConsumerTest() {
		String response = service.createConsumer(consumer);
		assertEquals("Consumer criado com sucesso", response);
	}

	@Test
	public void updateConsumerTestIDnaoExiste() {
		consumer.setName("Test Update");
		String response = service.updateConsumer(consumer);
		assertEquals(
				"Consumer com este ID nao existe, para adicionar um Consumer tente o metodo proprio para isso: createConsumer.",
				response);
	}

	@Test
	public void setBalanceTestFarmacia() {
		Mockito.when(repo.findByDrugstoreNumber(consumer.getDrugstoreNumber())).thenReturn(consumer);
		String response = service.setBalance(consumer.getDrugstoreNumber(), 2);
		assertEquals("Saldo do cartão atualizado com sucesso.", response);
	}
	
	@Test
	public void setBalanceTestComida() {
		Mockito.when(repo.findByFoodCardNumber(consumer.getFoodCardNumber())).thenReturn(consumer);
		String response = service.setBalance(consumer.getFoodCardNumber(), 2);
		assertEquals("Saldo do cartão atualizado com sucesso.", response);
	}
	@Test
	public void setBalanceTestCombustivel() {
		Mockito.when(repo.findByFuelCardNumber(consumer.getFuelCardNumber())).thenReturn(consumer);
		String response = service.setBalance(consumer.getFuelCardNumber(), 2);
		assertEquals("Saldo do cartão atualizado com sucesso.", response);
	}
}
