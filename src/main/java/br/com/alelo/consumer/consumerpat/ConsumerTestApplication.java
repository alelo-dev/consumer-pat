package br.com.alelo.consumer.consumerpat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ConsumerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTestApplication.class, args);
	}

}
