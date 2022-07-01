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
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ExtractService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ExtractControllerTest {
	@MockBean
	private ExtractRepository repoExtract;
	
	@MockBean
	private ExtractService service;
	
	@InjectMocks
	private ExtractController controller;
	
	@Mock
	Extract extract;

	@Mock
	List<Extract> extractList;
	
	@Mock
	List<Consumer> consumerList = new ArrayList<Consumer>();
	
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
		extractList.add(extract);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void listExtractsTest() {
		Mockito.when(repoExtract.getAllExtractList()).thenReturn(extractList);
		Mockito.when(service.listAllExtracts()).thenReturn(extractList);
		List<Extract> listTest = controller.listExtracts();
		assertEquals(extractList, listTest);
	}
	
	@Test
	public void buyTest() throws Exception {
		Mockito.when(service.buy(1, extract)).thenReturn("Compra feita! Saldo do cartao atualizado com sucesso.");
		ResponseEntity<Object> response = controller.buy(1, extract);
		assertEquals(new ResponseEntity<Object>("Compra feita! Saldo do cartao atualizado com sucesso.", HttpStatus.OK), response);
	}
}
