package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.service.impl.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;

@SpringBootTest
class ConsumerTestApplicationTests {

	@Mock
	private ConsumerService consumerService;

	private ConsumerDTO consumer;

	@BeforeEach
	public void setup() {
		consumer = ConsumerDTO
			.builder()
			.birthDate(new Date())
			.name("Teste")
			.documentNumber(1234567)
			.mobilePhoneNumber(3131231)
			.residencePhoneNumber(1231231)
			.phoneNumber(1232131)
			.email("teste@teste.com")
			.foodCardNumber(123456789)
			.foodCardBalance(100.0)
			.fuelCardNumber(987654321)
			.fuelCardBalance(0.0)
			.drugstoreCardNumber(192837465)
			.drugstoreCardBalance(400.0)
			.street("Rua teste")
			.number(0)
			.city("Brasilia")
			.country("DF")
			.postalCode(72000000)
			.build();
		Mockito.when(consumerService.save(Mockito.any(ConsumerDTO.class))).thenReturn(consumer);
	}

	@Test
	void saveConsumerTest() {
		ConsumerDTO dto = consumerService.save(consumer);
		Assert.isTrue(dto.getName().equals("Teste"), "Deverá retornar o nome Teste.");
	}

}
