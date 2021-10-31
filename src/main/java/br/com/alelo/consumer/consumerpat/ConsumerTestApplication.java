package br.com.alelo.consumer.consumerpat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration 
@ComponentScan
@SpringBootApplication
public class ConsumerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTestApplication.class, args);
	}

}
