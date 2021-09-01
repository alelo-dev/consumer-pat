package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class ConsumerFoodCard implements ConsumerStrategy {

	@Override
	public Consumer setCardBalance(ConsumerRepository repository, Consumer consumer, double foodCardBalance) {
		consumer.setFoodCardBalance(consumer.getFoodCardBalance() + foodCardBalance);
		return repository.save(consumer);
	}

	@Override
	public Consumer update(ConsumerRepository repository, Consumer consumer) {
		return repository.save(consumer);
	}

	@Override
	public Consumer buy(ConsumerRepository repository, Consumer consumer, double value) {
		Double cashback = (value / 100) * 10;
		value = value - cashback;
		consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
		return repository.save(consumer);
	}

}
