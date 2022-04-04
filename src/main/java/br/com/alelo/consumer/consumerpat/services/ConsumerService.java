package br.com.alelo.consumer.consumerpat.services;
import java.util.List;
import java.util.Optional;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerService {


	List<Consumer> getAllConsumersList();
	
	Consumer save(Consumer consumer);

	Optional<Consumer> getConsumerById( long id);

}