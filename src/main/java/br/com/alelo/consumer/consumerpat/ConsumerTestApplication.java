package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConsumerTestApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerTestApplication.class, args);
	}

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	EstablishmentRepository establishmentRepository;

	@Override
	public void run(String... args) throws Exception {

		Consumer consumer1 = new Consumer();

		consumer1.setName("jane");
		List<Card> cardList = new ArrayList<>();

		Card card11 = new Card();
		card11.setNumber(111);
		card11.setCardType(1);
		card11.setConsumer(consumer1);
		card11.setCardBalance(0.0);

	 	Card card12 = new Card();
		card12.setConsumer(consumer1);
		card12.setCardType(2);
		card12.setNumber(112);
		card12.setCardBalance(0);

		Card card13 = new Card();
		card13.setConsumer(consumer1);
		card13.setCardType(3);
		card13.setNumber(113);
		card13.setCardBalance(0);

		cardList.add(card11);
        cardList.add(card12);
		cardList.add(card13);

		consumer1.setCards(cardList);

		consumerRepository.save(consumer1);


		Establishment est1 = new Establishment();
		est1.setId(1);
		est1.setName("Mac Donalds");
		est1.setType(1);
		establishmentRepository.save(est1);

		Establishment est2 = new Establishment();
		est2.setId(2);
		est2.setName("Drogaria Sao Paulo");
		est2.setType(2);
		establishmentRepository.save(est2);


		Establishment est3 = new Establishment();
		est3.setId(3);
		est3.setName("Posto BR");
		est3.setType(3);
		establishmentRepository.save(est3);
	}
}
