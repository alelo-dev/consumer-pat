package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class ConsumerFuelCard implements ConsumerStrategy {

	@Override
	public Consumer setCardBalance(ConsumerRepository repository, Consumer consumer, double cardBalance) {
		consumer.setFuelCardBalance(consumer.getFuelCardBalance() + cardBalance);
		return repository.save(consumer);
	}

	@Override
	public Consumer update(ConsumerRepository repository, Consumer consumer) {
		return repository.save(consumer);
	}

	@Override
	public Consumer buy(ConsumerRepository repository, Consumer consumer, double value) {
		Double tax = (value / 100) * 35;
		value = value + tax;
		consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
		return repository.save(consumer);
	}
}
