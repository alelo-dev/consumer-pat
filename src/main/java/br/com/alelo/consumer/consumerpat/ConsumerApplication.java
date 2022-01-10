package br.com.alelo.consumer.consumerpat;

import br.com.alelo.consumer.consumerpat.entity.Establishment;
import br.com.alelo.consumer.consumerpat.entity.TypeCard;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.TypeCardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@SpringBootApplication(scanBasePackages = "br.com.alelo.consumer")
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	CommandLineRunner initCard(TypeCardRepository repository) {
		return args -> {
			repository.deleteAll();

			List<TypeCard> typesCard = new ArrayList<>();
			typesCard.add(TypeCard.builder()
					.idTypeCard(1L)
					.typeCard("ALIMENTACAO")
					.build());
			typesCard.add(TypeCard.builder()
					.idTypeCard(2L)
					.typeCard("FARMACIA")
					.build());
			typesCard.add(TypeCard.builder()
					.idTypeCard(3L)
					.typeCard("COMBUSTIVEL")
					.build());
			typesCard.stream()
					.map(t -> repository.save(t))
					.forEach(System.out::println);
		};
	}

	@Bean
	CommandLineRunner initEstablishment(EstablishmentRepository repository) {
		return args -> {
			List<Establishment> establishments = new ArrayList<>();
			establishments.add(Establishment.builder().idEstablishment("1").nameEstablishment("POSTO MAUA").build());
			establishments.add(Establishment.builder().idEstablishment("2").nameEstablishment("DROGARIA MAUA").build());
			establishments.add(Establishment.builder().idEstablishment("3").nameEstablishment("ATACADÃO").build());
			establishments.stream()
					.map(e -> repository.save(e))
					.forEach(System.out::println);
		};
	}
}
