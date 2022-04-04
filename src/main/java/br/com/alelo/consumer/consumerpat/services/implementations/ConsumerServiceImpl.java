package br.com.alelo.consumer.consumerpat.services.implementations;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;


@Service
public class ConsumerServiceImpl implements ConsumerService {

	
	private static final Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	
    @Autowired
    ConsumerRepository consumerRepository;


	@Override
	public List<Consumer> getAllConsumersList() {
		
		log.info("Executing getAllConsumersList()");
		
		return consumerRepository.getAllConsumersList();
	}


	@Override
	public Consumer save(Consumer consumer) {

		log.info("Executing save()");
		
		return consumerRepository.save( consumer );
	}


	@Override
	public Optional<Consumer> getConsumerById( long id ) {
		
		log.info("Getting consumer with id: ", id );
		
		return consumerRepository.findById( id );
	}
	
}