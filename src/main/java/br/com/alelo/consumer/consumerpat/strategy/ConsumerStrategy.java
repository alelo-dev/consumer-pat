package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public interface ConsumerStrategy {

	Consumer setCardBalance(ConsumerRepository repository, Consumer consumer, double cardBalance);

	Consumer update(ConsumerRepository repository, Consumer consumer);
	
	Consumer buy(ConsumerRepository repository, Consumer consumer, double value);
}
