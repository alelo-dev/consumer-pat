package br.com.alelo.consumer.consumerpat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.alelo.consumer.consumerpat.Mocks;
import br.com.alelo.consumer.consumerpat.application.card.balance.request.CardBalanceRequest;
import br.com.alelo.consumer.consumerpat.application.card.buy.request.CardBuyRequest;
import br.com.alelo.consumer.consumerpat.application.card.buy.response.TransactionResponse;
import br.com.alelo.consumer.consumerpat.application.consumer.create.request.CreateConsumerRequest;
import br.com.alelo.consumer.consumerpat.application.consumer.search.response.ConsumerResponseList;
import br.com.alelo.consumer.consumerpat.application.consumer.update.request.UpdateConsumerRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class ControllerTest {

	private static final Logger log = LoggerFactory.getLogger(ControllerTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	@Order(1)
	void testCreateConsumer() {

		try {
			final String baseUrl = "http://localhost:" + randomServerPort + "/consumer/createConsumer";
			URI uri = new URI(baseUrl);

			HttpHeaders headers = new HttpHeaders();
			CreateConsumerRequest createConsumerRequest = Mocks.getCreateConsumerRequest();
			HttpEntity<CreateConsumerRequest> request = new HttpEntity<>(createConsumerRequest, headers);

			ResponseEntity<Void> result = this.restTemplate.postForEntity(uri, request, Void.class);
			assertEquals(HttpStatus.CREATED, result.getStatusCode());
		} catch (Exception e) {
			fail("ocorreu um erro ");
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	void testUpdateConsumer() {

		try {
			final String baseUrl = "http://localhost:" + randomServerPort + "/consumer/updateConsumer";
			URI uri = new URI(baseUrl);

			HttpHeaders headers = new HttpHeaders();
			UpdateConsumerRequest createConsumerRequest = Mocks.getUpdateConsumerRequest();
			HttpEntity<UpdateConsumerRequest> request = new HttpEntity<>(createConsumerRequest, headers);

			ResponseEntity<Void> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);
			assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		} catch (Exception e) {
			fail("ocorreu um erro ");
			e.printStackTrace();
		}
	}

	@Test
	@Order(3)
	void testListAllConsumers() {
		try {
			URI uri = new URI("http://localhost:".concat("" + randomServerPort).concat("/consumer/consumerList"));

			ResponseEntity<ConsumerResponseList> result = this.restTemplate.getForEntity(uri,
					ConsumerResponseList.class);
			assertEquals(HttpStatus.OK, result.getStatusCode());

			ConsumerResponseList body = result.getBody();
			assertFalse(body.getConsumerList().isEmpty());
		} catch (Exception e) {
			fail("ocorreu um erro ");
			log.error("error {}", e);
		}
	}

	@Test
	@Order(4)
	void testSetBalance() {

		try {
			final String baseUrl = "http://localhost:" + randomServerPort + "/card/setcardbalance";
			URI uri = new URI(baseUrl);

			HttpHeaders headers = new HttpHeaders();
			CardBalanceRequest createConsumerRequest = Mocks.getCardBalanceRequest();
			HttpEntity<CardBalanceRequest> request = new HttpEntity<>(createConsumerRequest, headers);

			ResponseEntity<Void> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);
			assertEquals(HttpStatus.OK, result.getStatusCode());
		} catch (Exception e) {
			fail("ocorreu um erro ");
			e.printStackTrace();
		}
	}

	@Test
	@Order(6)
	void testBuy() {
		try {
			final String baseUrl = "http://localhost:" + randomServerPort + "/card/buy";
			URI uri = new URI(baseUrl);

			HttpHeaders headers = new HttpHeaders();
			CardBuyRequest createConsumerRequest = Mocks.getCardBuyRequest();
			HttpEntity<CardBuyRequest> request = new HttpEntity<>(createConsumerRequest, headers);

			ResponseEntity<TransactionResponse> result = this.restTemplate.exchange(uri, HttpMethod.POST, request,
					TransactionResponse.class);
			assertEquals(HttpStatus.OK, result.getStatusCode());
			TransactionResponse body = result.getBody();
			assertNotNull(body.getValue());
			assertNotNull(body.getValue());
		} catch (Exception e) {
			fail("ocorreu um erro ");
			e.printStackTrace();
		}
	}

}
