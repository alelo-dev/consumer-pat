package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.util.EstablishmentType;

@Component
public class Fuel implements BalanceAndBuyStrategy {

	@Autowired
	ConsumerRepository repository;

	@Override
	public Consumer setBalance(int cardNumber, double value) {
		Consumer consumer = repository.findByFuelCardNumber(cardNumber);
		consumer.setFuelCardBalance(consumer.getFuelCardBalance() + value);
		repository.save(consumer);
		return consumer;
	}

	@Override
	public boolean buy(int cardNumber, double value) {

		Double tax = (value / 100) * 35;
		value = value + tax;

		Consumer consumer = repository.findByFuelCardNumber(cardNumber);

		if (consumer != null) {

			if (consumer.getFuelCardBalance() - value < 0) {
				return false;
			}

			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - value);
			repository.save(consumer);
			return true;
		}

		return false;

	}

	@Override
	public EstablishmentType getEstablishmentType() {
		return EstablishmentType.FUEL;
	}

}
