package br.com.alelo.consumer.consumerpat.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;

import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.DataGenerator;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConsumerControllerTest {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private TestRestTemplate template;

	private List<Consumer> mockConsumersList;
	
	@BeforeAll
	public void setUp() {
		mockConsumersList = DataGenerator.generateConsumerList(50);
		consumerRepository.saveAll(mockConsumersList);
	}
	
	@Test
	public void getPaginatedConsumerListTest() throws RestClientException, URISyntaxException {
		ResponseEntity<List> response = template.getForEntity(new URI(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS)), List.class);
        Assert.isTrue(response.getBody().size() == 20, "The default paginated size must be 20");
	}
	
	@Test
	public void findOneConsumerTest() throws RestClientException, URISyntaxException {
		Consumer mockConsumer = consumerRepository.findByCpf(mockConsumersList.get(0).getCpf());
		ResponseEntity<Consumer> response = template.getForEntity(new URI(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS).concat("/" + mockConsumer.getId())), Consumer.class);
        Assert.isTrue(response.getBody().getCpf() !=null, "Not found one consumer");
	}

	@Test
	public void createOneConsumerTest() throws RestClientException, URISyntaxException {
		Consumer mockConsumer = DataGenerator.generateConsumer();
		ResponseEntity<Consumer> response = template.postForEntity(new URI(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS)), mockConsumer, Consumer.class);
        Consumer consumer = response.getBody();
        Assert.isTrue(consumer != null, "Consumer not created");
		Assert.isTrue(response.getBody().equals(mockConsumer), "Consumer creation invalid");
	}
	
	@Test
	public void updateOneConsumerTest() throws RestClientException, URISyntaxException {
		Consumer mock = DataGenerator.generateConsumer();
		ResponseEntity<Consumer> response = template.postForEntity(new URI(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS)), mock, Consumer.class);
        Consumer mockConsumer = response.getBody();
		List<Card> oldCards = new ArrayList<>();
		mockConsumer.getCards().stream().forEach(c -> oldCards.add(new Card(c.getNumber(), c.getType(), c.getBalance(), c.getConsumer(), c.getExpirationDate())));
		mockConsumer.setEmail("test@test.com");
		mockConsumer.getCards().stream().forEach(c -> c.credit(BigDecimal.valueOf(1000)));
		// update
		template.put(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS), mockConsumer, Consumer.class);
		// get the updated consumer
		ResponseEntity<Consumer> responseConsumer = template.getForEntity(new URI(ConsumerController.BASE_PATH.concat(ConsumerController.CONSUMERS).concat("/" + mockConsumer.getId())), Consumer.class);

		// checking the updated consumer
		Assert.isTrue(responseConsumer.getBody().getEmail().equals("test@test.com"), "Consumer updating invalid");
		
		// checking the cards of the updated consumer
		Assert.isTrue(responseConsumer.getBody().getCards().equals(oldCards), "Consumer cards updating invalid");
	}
}
