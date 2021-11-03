package br.com.alelo.consumer.consumerpat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.util.DataGenerator;

@Configuration
public class StartupLoadData implements ApplicationRunner {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// inserindo 500 registros ficticios de clientes
		consumerRepository.saveAll(DataGenerator.generateConsumerList(50));
		establishmentRepository.saveAll(DataGenerator.generateEstablishmentList(50));
	}
}
