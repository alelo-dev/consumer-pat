package br.com.alelo.consumer.consumerpat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.Extract;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.util.BalanceAndBuyStrategyFactory;
import br.com.alelo.consumer.consumerpat.util.Buy;
import br.com.alelo.consumer.consumerpat.util.EstablishmentType;

@Service
public class ConsumerPatService {

	@Autowired
	ConsumerRepository repository;

	@Autowired
	ExtractRepository extractRepository;

	@Autowired
	private BalanceAndBuyStrategyFactory strategyFactory;

	public List<Consumer> listAllConsumers() {

		return repository.getAllConsumersList();
	}

	public Consumer createConsumer(Consumer consumer) {

		if (repository.findByDrugstoreNumber(consumer.getDrugstoreNumber()) != null) {
		
			return null;
		}
		
		if (repository.findByFuelCardNumber(consumer.getFuelCardNumber()) != null) {
			
			return null;
		}
		
		if (repository.findByFoodCardNumber(consumer.getFoodCardNumber()) != null) {
			
			return null;
		}
		
		
		repository.save(consumer);
		return consumer;
	}

	public Optional<Consumer> updateConsumer(Consumer consumer) {

		Optional<Consumer> consumerToBeUpdated = repository.findById(consumer.getId());

		if (consumerToBeUpdated.isPresent()) {

			consumerToBeUpdated.get().setId(consumer.getId());
			consumerToBeUpdated.get().setBirthDate(consumer.getBirthDate());
			consumerToBeUpdated.get().setCity(consumer.getCity());
			consumerToBeUpdated.get().setCountry(consumer.getCountry());
			consumerToBeUpdated.get().setDocumentNumber(consumer.getDocumentNumber());
			consumerToBeUpdated.get().setEmail(consumer.getEmail());
			consumerToBeUpdated.get().setMobilePhoneNumber(consumer.getMobilePhoneNumber());
			consumerToBeUpdated.get().setName(consumer.getName());
			consumerToBeUpdated.get().setNumber(consumer.getNumber());
			consumerToBeUpdated.get().setPhoneNumber(consumer.getPhoneNumber());
			consumerToBeUpdated.get().setPortalCode(consumer.getPortalCode());
			consumerToBeUpdated.get().setResidencePhoneNumber(consumer.getResidencePhoneNumber());
			consumerToBeUpdated.get().setStreet(consumer.getStreet());
			repository.save(consumerToBeUpdated.get());

		}

		return repository.findById(consumer.getId());
	}

	private int findCurrentCard(int cardNumber) {

		Consumer consumer = repository.findByDrugstoreNumber(cardNumber);

		if (consumer != null) {
			return EstablishmentType.DRUGSTORE.getValue();
		}

		consumer = repository.findByFoodCardNumber(cardNumber);

		if (consumer != null) {

			return EstablishmentType.FOOD.getValue();
		}

		consumer = repository.findByFuelCardNumber(cardNumber);

		if (consumer != null) {

			return EstablishmentType.FUEL.getValue();
		}

		return 0;
	}

	private BalanceAndBuyStrategy findCurrentStrategyEstablismentType(int establishmentType) {

		Optional<EstablishmentType> establishmentTypeSelected = Stream.of(EstablishmentType.values())
				.filter(item -> item.getValue() == establishmentType).findAny();

		return strategyFactory.findStrategy(establishmentTypeSelected.get());

	}

	public Consumer setBalance(int cardNumber, double value) {
		Optional<EstablishmentType> establishmentTypeSelected = Stream.of(EstablishmentType.values())
				.filter(item -> item.getValue() == this.findCurrentCard(cardNumber)).findAny();

		BalanceAndBuyStrategy strategy = this
				.findCurrentStrategyEstablismentType(establishmentTypeSelected.get().getValue());
		return strategy.setBalance(cardNumber, value);
	}

	public boolean buy(Buy buy) {

		BalanceAndBuyStrategy strategy = this.findCurrentStrategyEstablismentType(buy.getEstablishmentType());

		if (strategy.buy(buy.getCardNumber(), buy.getValue())) {
			Extract extract = new Extract(buy.getEstablishmentName(), buy.getProductDescription(), new Date(),
					buy.getCardNumber(), buy.getValue());
			extractRepository.save(extract);
			return true;
		}

		return false;
	}
}
