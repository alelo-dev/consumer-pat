package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class ConsumerDrugstoreCard implements ConsumerStrategy {

	@Override
	public Consumer setCardBalance(ConsumerRepository repository, Consumer consumer, double cardBalance) {
		consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() + cardBalance);
		return repository.save(consumer);
	}

	@Override
	public Consumer update(ConsumerRepository repository, Consumer consumer) {
		return repository.save(consumer);
	}

	@Override
	public Consumer buy(ConsumerRepository repository, Consumer consumer, double value) {
		consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - value);
		return repository.save(consumer);
	}
}
