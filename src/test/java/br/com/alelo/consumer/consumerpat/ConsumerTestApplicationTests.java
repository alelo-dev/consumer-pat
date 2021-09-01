package br.com.alelo.consumer.consumerpat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.alelo.consumer.consumerpat.util.ConsumerUtil;

@SpringBootTest
class ConsumerTestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void ConsumerUtilTest() throws Exception {
		Assertions.assertEquals(ConsumerUtil.foodCardCashback(99.00), 9.9);
		Assertions.assertEquals(ConsumerUtil.fuelCardFee(99.00), 34.65);
	}
}
