package br.com.alelo.consumer.consumerpat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.ConsumerServiceImpl;
import br.com.alelo.consumer.consumerpat.service.exception.ConsumerException;

@SpringBootTest
class ConsumerTestApplicationTests {
	private ConsumerRepository consumerRepository;
	private ExtractRepository extractRepository;

	private ConsumerService service;

	@BeforeEach
	void contextLoads() {
		consumerRepository = mock(ConsumerRepository.class);
		extractRepository = mock(ExtractRepository.class);
		service = new ConsumerServiceImpl(consumerRepository, extractRepository);
	}

	@Test
	@DisplayName("Não deve ser possível alterar o saldo do cartão - Alimentação")
	void whenChangeFoodBalance_ShouldThrowException() {
		Consumer initialConsumer = ConsumerProvider.createInitialConsumer(15);
		Consumer consumer = ConsumerProvider.createInitialConsumer(15);
		when(consumerRepository.findById(any())).thenReturn(Optional.of(initialConsumer));

		consumer.setFoodCardBalance(30);
		assertThrows(ConsumerException.class, () -> service.updateConsumer(consumer));

	}

	@Test
	@DisplayName("Não deve ser possível alterar o saldo do cartão - Farmácia")
	void whenChangeDrugStoreBalance_ShouldThrowException() {
		Consumer initialConsumer = ConsumerProvider.createInitialConsumer(15);
		Consumer consumer = ConsumerProvider.createInitialConsumer(15);
		when(consumerRepository.findById(any())).thenReturn(Optional.of(initialConsumer));

		consumer.setDrugstoreCardBalance(30);
		assertThrows(ConsumerException.class, () -> service.updateConsumer(consumer));

	}

	@Test
	@DisplayName("Não deve ser possível alterar o saldo do cartão - Combustível")
	void whenChangeFuelBalance_ShouldThrowException() {
		Consumer initialConsumer = ConsumerProvider.createInitialConsumer(15);
		Consumer consumer = ConsumerProvider.createInitialConsumer(15);
		when(consumerRepository.findById(initialConsumer.getId())).thenReturn(Optional.of(initialConsumer));

		consumer.setFuelCardBalance(30);
		assertThrows(ConsumerException.class, () -> service.updateConsumer(consumer));

	}

	@Test
	@DisplayName("Deve salvar a alteração")
	void whenUpdate_ShouldSaveTheConsumer() throws ConsumerException {
		Consumer initialConsumer = ConsumerProvider.createInitialConsumer(15);
		Consumer consumer = ConsumerProvider.createInitialConsumer(15);
		consumer.setBirthDate(new Date());
		when(consumerRepository.findById(initialConsumer.getId())).thenReturn(Optional.of(initialConsumer));
		service.updateConsumer(consumer);
		verify(consumerRepository, times(1)).save(any());

	}

	@Test
	@DisplayName("Deve descontar do saldo de alimentação")
	void whenBuyWithFoodCard_ShouldSubtractFromBalance() throws ConsumerException {
		Consumer consumer = mock(Consumer.class);
		when(consumer.getFoodCardBalance()).thenReturn(100.0);
		when(consumerRepository.findByFoodCardNumber(any(Integer.class))).thenReturn(consumer);

		double value = 30.0;
		service.buy(1, "teste establishment", consumer.getFoodCardNumber(), "Teste produto", value);
		Double cashback = (value / 100) * 10;
		double expected = 100.0 - (value - cashback);
		verify(consumer).setFoodCardBalance(expected);
	}

	@Test
	@DisplayName("Deve descontar do saldo de Farmácia")
	void whenBuyWithDrugstoreCard_ShouldSubtractFromBalance() throws ConsumerException {
		Consumer consumer = mock(Consumer.class);
		when(consumer.getDrugstoreCardBalance()).thenReturn(100.0);
		double value = 30.0;

		when(consumerRepository.findByDrugstoreNumber(any(Integer.class))).thenReturn(consumer);
		service.buy(2, "teste establishment", consumer.getDrugstoreNumber(), "Teste produto", value);

		double expected = 100.0 - 30.0;
		verify(consumer).setDrugstoreCardBalance(expected);
	}

	@Test
	@DisplayName("Deve descontar do saldo de Combustível")
	void whenBuyFuelCard_ShouldSubtractFromBalance() throws ConsumerException {
		Consumer consumer = mock(Consumer.class);
		when(consumer.getFuelCardBalance()).thenReturn(100.0);
		double value = 30.0;

		when(consumerRepository.findByFuelCardNumber(any(Integer.class))).thenReturn(consumer);
		service.buy(3, "teste establishment", consumer.getFuelCardNumber(), "Teste produto", value);
		double tax = (value / 100) * 35;
		double expected = 100.0 - (value + tax);
		verify(consumer).setFuelCardBalance(expected);
	}

	@Test
	@DisplayName("Deve depositar no saldo de Combustível")
	void whenAddByFuelNumber_ShouldIncreaseBalance() throws ConsumerException {
		double initialBalance = 100;
		Consumer consumer = mock(Consumer.class);
		when(consumer.getFuelCardBalance()).thenReturn(initialBalance);
		double value = 30.0;
	
		when(consumerRepository.findByDrugstoreNumber(any(Integer.class))).thenReturn(null);
		when(consumerRepository.findByFoodCardNumber(any(Integer.class))).thenReturn(null);
		when(consumerRepository.findByFuelCardNumber(any(Integer.class))).thenReturn(consumer);
		service.addBalance(99, value);
		double expected = initialBalance + value;
		verify(consumer).setFuelCardBalance(expected);
	}

	@Test
	@DisplayName("Deve depositar no saldo de Alimentação")
	void whenAddByFoodCardNumber_ShouldIncreaseBalance() throws ConsumerException {
		double initialBalance = 100;
		Consumer consumer = mock(Consumer.class);
		when(consumer.getFoodCardBalance()).thenReturn(initialBalance);
		double value = 30.0;
	
		when(consumerRepository.findByDrugstoreNumber(any(Integer.class))).thenReturn(null);
		when(consumerRepository.findByFuelCardNumber(any(Integer.class))).thenReturn(null);
		when(consumerRepository.findByFoodCardNumber(any(Integer.class))).thenReturn(consumer);
		service.addBalance(99, value);
		double expected = initialBalance + value;
		verify(consumer).setFoodCardBalance(expected);
	}

	@Test
	@DisplayName("Deve depositar no saldo de Farmácia")
	void whenAddByDrugstoreCardNumber_ShouldIncreaseBalance() throws ConsumerException {
		double initialBalance = 100;
		Consumer consumer = mock(Consumer.class);
		when(consumer.getDrugstoreCardBalance()).thenReturn(initialBalance);
		double value = 30.0;
		//
		when(consumerRepository.findByDrugstoreNumber(any(Integer.class))).thenReturn(consumer);
		when(consumerRepository.findByFuelCardNumber(any(Integer.class))).thenReturn(null);
		when(consumerRepository.findByFoodCardNumber(any(Integer.class))).thenReturn(null);
		service.addBalance(99, value);
		double expected = initialBalance + value;
		verify(consumer).setDrugstoreCardBalance(expected);
	}

}
