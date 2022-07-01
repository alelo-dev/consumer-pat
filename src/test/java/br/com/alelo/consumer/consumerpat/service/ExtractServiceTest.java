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
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ExtractServiceTest {
	@MockBean
	private ExtractRepository repo;
	
	@MockBean
	private ConsumerRepository repoConsumer;
	
	@InjectMocks
	private ExtractService service;

	@Mock
	Extract extract;

	@Mock
	Consumer consumer;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		extract.setCardNumber(1);
		extract.setDateBuy(new Date());
		extract.setEstablishmentName("Test");
		extract.setEstablishmentNameId(1);
		extract.setId(1);
		extract.setProductDescription("Test");
		extract.setValue(5.0);
		

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
	public void listAllExtracts() {
		Mockito.when(repo.getAllExtractList()).thenReturn(new ArrayList<Extract>());
		List<Extract> response = service.listAllExtracts();
		assertEquals(new ArrayList<Extract>(), response);
	}
	
	@Test
	public void buyComida() {
		Mockito.when(repoConsumer.findByFoodCardNumber(extract.getCardNumber())).thenReturn(consumer);
		String response = service.buy(1, extract);
		assertEquals("Compra feita! Saldo do cartao atualizado com sucesso.", response);
	}
	
	@Test
	public void buyFarmacia() {
		Mockito.when(repoConsumer.findByDrugstoreNumber(extract.getCardNumber())).thenReturn(consumer);
		String response = service.buy(2, extract);
		assertEquals("Compra feita! Saldo do cartao atualizado com sucesso.", response);
	}
	
	@Test
	public void buyCombustivel() {
		Mockito.when(repoConsumer.findByFuelCardNumber(extract.getCardNumber())).thenReturn(consumer);
		String response = service.buy(3, extract);
		assertEquals("Compra feita! Saldo do cartao atualizado com sucesso.", response);
	}
	
//	@Test
//	public void buySemSaldo() {
//		Mockito.when(repoConsumer.findByFuelCardNumber(extract.getCardNumber())).thenReturn(consumer);
//		String response = service.buy(1, extract);
//		assertEquals("Saldo insuficiente para realizar a compra!", response);
//	}
}
