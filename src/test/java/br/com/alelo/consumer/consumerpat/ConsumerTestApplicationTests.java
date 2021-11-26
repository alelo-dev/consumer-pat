package br.com.alelo.consumer.consumerpat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alelo.consumer.consumerpat.controller.ConsumerController;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;

@SpringBootTest
class ConsumerTestApplicationTests {

	private static final String NUMERO_CARTAO_JA_CADASTRADO_MSG = "Número de cartão já cadastrado: ";

	@Autowired
	private ConsumerController consumerController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(consumerController).isNotNull();
	}

	@Test
	public void createConsumerSuccess() throws Exception {

		Consumer consumer = createConsumer();

		consumerController.createConsumer(consumer);

		List<Consumer> lista = consumerController.listAllConsumers();

		assertTrue(!lista.isEmpty());
	}

	@Test
	public void createConsumerCardNumberValidation() {
		try {
			Consumer consumer = createConsumer();

			consumerController.createConsumer(consumer);
			consumer.setId(2);
			consumerController.createConsumer(consumer);
			
			assertTrue(false);
		} catch (BusinessException e) {
			assertTrue(e.getMessage().contains(NUMERO_CARTAO_JA_CADASTRADO_MSG));
		}
	}

	private Consumer createConsumer() {
		Consumer consumer = new Consumer();
		consumer.setId(1);
		consumer.setBirthDate(new Date());
		consumer.setCity("RJ");
		consumer.setCountry("BR");
		consumer.setDocumentNumber(123456);
		consumer.setDrugstoreCardBalance(100D);
		consumer.setDrugstoreCardNumber(1111);
		consumer.setEmail("teste@xd.com");
		consumer.setFoodCardBalance(100D);
		consumer.setFoodCardNumber(2222);
		consumer.setFuelCardBalance(100D);
		consumer.setFuelCardNumber(333);
		consumer.setMobilePhoneNumber(11223344);
		consumer.setName("luis alberto ferreira");
		consumer.setNumber(12312321);
		consumer.setPortalCode(226684123);
		return consumer;
	}

}
