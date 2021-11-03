package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Establishment;
import br.com.alelo.consumer.consumerpat.model.Extract;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.util.DataGenerator;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OperationControllerTest {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	@Autowired
	private TestRestTemplate template;

	@BeforeAll
	public void setUp() {
		consumerRepository.saveAll(DataGenerator.generateConsumerList(50));
	}
	
	@Test
	public void creditOperationTest() throws RestClientException, URISyntaxException {
		Consumer mockConsumer = DataGenerator.generateConsumer();
		template.postForEntity(new URI(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS)), mockConsumer, Consumer.class);
		Card addedCard = mockConsumer.getCards().get(0);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
	    params.add("cardNumber", addedCard.getNumber());
	    params.add("value", "1000"); // valor a creditar
	    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
		ResponseEntity<Card> response = template.postForEntity(new URI(OperationController.BASE_PATH.concat(OperationController.CREDIT)),requestEntity, Card.class);
		Card responseCard = response.getBody();
		Assert.isTrue(addedCard.getBalance().add(BigDecimal.valueOf(1000)).equals(responseCard.getBalance()), "Valores não conferem");
	}
	
	@Test
	public void debitOperationTest() throws RestClientException, URISyntaxException {
		Consumer mockConsumer = DataGenerator.generateConsumer();
		Card addedCard = mockConsumer.getCards().get(0);
		addedCard.credit(BigDecimal.valueOf(2000));
		Establishment mockEstablishment = DataGenerator.generateEstablishment(addedCard.getType());   // gerando estabelecimento com mesmo tipo do cartão 
		establishmentRepository.save(mockEstablishment);
		template.postForEntity(new URI(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS)), mockConsumer, Consumer.class);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
	    params.add("establishmentType", String.valueOf(addedCard.getType().getId()));
	    params.add("establishmentName", mockEstablishment.getName());
	    params.add("cardNumber", String.valueOf(addedCard.getNumber()));
	    params.add("productDescription", "Produto teste");
	    params.add("value", "1000"); // valor a debitar
	    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
		ResponseEntity<Extract> response = template.postForEntity(new URI(OperationController.BASE_PATH.concat(OperationController.DEBIT)),requestEntity, Extract.class);
		Extract responseExtract = response.getBody();
		Assert.isTrue(responseExtract.getValue().equals(BigDecimal.valueOf(1000)), "Valores não conferem");
	}

}
