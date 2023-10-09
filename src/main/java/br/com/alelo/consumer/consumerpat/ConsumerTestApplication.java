package br.com.alelo.consumer.consumerpat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.alelo.consumer.consumerpat")
public class ConsumerTestApplication {

	public static void main(String... args) {
		SpringApplication.run(ConsumerTestApplication.class, args);
	}

}
