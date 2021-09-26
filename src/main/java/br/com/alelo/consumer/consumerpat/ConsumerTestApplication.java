package br.com.alelo.consumer.consumerpat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages={"br.com.alelo.consumer.consumerpat.controller", 
		"br.com.alelo.consumer.consumerpat.service", 
		"br.com.alelo.consumer.consumerpat.handler", 
		"br.com.alelo.consumer.consumerpat.exception",
		"br.com.alelo.consumer.consumerpat.processor"})
@EnableJpaRepositories("br.com.alelo.consumer.consumerpat.repository")
public class ConsumerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTestApplication.class, args);
	}

}
