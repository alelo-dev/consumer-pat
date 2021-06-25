package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ConsumerTestApplication implements CommandLineRunner {

	@Autowired
	ConsumerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTestApplication.class, args);
	}

	/*
	 *		Deixei comentado, caso seja necessário testar com a lista de 500 nomes.
	 */

	@Override
	public void run(String... args) throws Exception {


//		List<Consumer> consumerList = new ArrayList<>();
//
//		for (int i = 0; i < 500; i++) {
//			Consumer cons = new Consumer();
//
//			cons.setName("Romulo " + i+1);
//			cons.setDocumentNumber(123456 + i);
//
//			cons.setFoodCardNumber(1234567890123456L + i);
//			cons.setFoodCardBalance(20 * i);
//
//			cons.setDrugstoreNumber(1111111111111111L + i);
//			cons.setDrugstoreCardBalance(5 * i + 2);
//
//			cons.setFuelCardNumber(2222222222222222L + i);
//			cons.setFuelCardBalance(30 * i);
//
//			consumerList.add(cons);
//		}
//		repository.saveAll(consumerList);
	}
}
