package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.EstablishmentType;

@Component
public class Food implements BalanceAndBuyStrategy {

	@Autowired
	ConsumerRepository repository;

	@Override
	public Consumer setBalance(int cardNumber, double value) {

		Consumer consumer = repository.findByFoodCardNumber(cardNumber);
		consumer.setFoodCardBalance(consumer.getFoodCardBalance() + value);
		repository.save(consumer);
		return consumer;

	}

	@Override
	public boolean buy(int cardNumber, double value) {
		Double cashback = (value / 100) * 10;
		value = value - cashback;

		Consumer consumer = repository.findByFoodCardNumber(cardNumber);

		if (consumer != null) {
			
			if (consumer.getFoodCardBalance() - value < 0) {
				return false;
			}
			
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - value);
			repository.save(consumer);
			return true;
		}
		
		return false;

	}

	@Override
	public EstablishmentType getEstablishmentType() {

		return EstablishmentType.FOOD;
	}

}
