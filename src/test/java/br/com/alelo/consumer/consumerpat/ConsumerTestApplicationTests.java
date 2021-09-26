package br.com.alelo.consumer.consumerpat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class ConsumerTestApplicationTests {

	@LocalServerPort
	int randomServerPort;
}
