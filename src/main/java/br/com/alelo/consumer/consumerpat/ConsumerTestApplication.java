package br.com.alelo.consumer.consumerpat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"br.com.alelo.consumer.consumerpat.entity"})
@EnableJpaRepositories(basePackages = {"br.com.alelo.consumer.consumerpat.repository"})
@ComponentScan(basePackages = {"br.com.alelo.consumer.consumerpat.service", "br.com.alelo.consumer.consumerpat.controller",
								"br.com.alelo.consumer.consumerpat.exception"})
@SpringBootApplication
public class ConsumerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTestApplication.class, args);
	}

}
