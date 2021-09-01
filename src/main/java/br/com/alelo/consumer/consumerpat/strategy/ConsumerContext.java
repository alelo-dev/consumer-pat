package br.com.alelo.consumer.consumerpat.strategy;

import java.util.Optional;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class ConsumerContext {

	private ConsumerStrategy consumerStrategy = null;
	private Consumer consumer = null;
	private ConsumerType consumerType = null;

	public void setStrategy(ConsumerRepository repository, Long cardNumber) {
		consumerStrategy = checkCardType(repository, cardNumber);
	}

	public ConsumerStrategy setStrategy(ConsumerRepository repository, Consumer consumer) {
		if ((consumerStrategy = checkCardType(repository, consumer.getDrugstoreNumber())) != null) {
			return consumerStrategy;
		} else if ((consumerStrategy = checkCardType(repository, consumer.getFoodCardNumber())) != null) {
			return consumerStrategy;
		} else if ((consumerStrategy = checkCardType(repository, consumer.getFuelCardNumber())) != null) {
			return consumerStrategy;
		}
		return null;

	}
	
//	public ConsumerStrategy setStrategy(ConsumerRepository repository, Integer establishmentType, Long cardNumber) {
//		
//		consumerStrategy = checkCardType(repository, cardNumber);
//		
//		if (establishmentType.equals(1)) {
//			return consumerStrategy = new ConsumerFoodCard();
//		} else if (establishmentType.equals(2)) {
//			return consumerStrategy = new ConsumerDrugstoreCard();
//		} else {
//			return consumerStrategy = new ConsumerFuelCard();
//		}
//	}

	private ConsumerStrategy checkCardType(ConsumerRepository repository, Long cardNumber) {
		if ((consumer = repository.findByDrugstoreNumber(cardNumber)) != null) {
			consumerType = ConsumerType.DRUGSTORE;
			return new ConsumerDrugstoreCard();
		} else if ((consumer = repository.findByFoodCardNumber(cardNumber)) != null) {
			consumerType = ConsumerType.FOOD;
			return new ConsumerFoodCard();
		} else if ((consumer = repository.findByFuelCardNumber(cardNumber)) != null) {
			consumerType = ConsumerType.FUEL;
			return new ConsumerFuelCard();
		}
		return null;
	}
	
	public Consumer setCardBalance(ConsumerRepository repository, double cardBalance) {
		return consumerStrategy.setCardBalance(repository, consumer, cardBalance);
	}

	public Consumer update(ConsumerRepository repository, Consumer newConsumer) {
		newConsumer.setId(consumer.getId());
		return consumerStrategy.update(repository, newConsumer);
	}

	public Consumer create(ConsumerRepository repository, Consumer newConsumer) {
		if (consumerStrategy == null) {
			return repository.save(newConsumer);
		}
		return null;
	}
	
	public Optional<Consumer> buy(ConsumerRepository repository, Integer establishmentType, double value) {
		if (consumerType.getValue() != establishmentType) {
			return Optional.empty();
		}
		return Optional.of(consumerStrategy.buy(repository, consumer, value));
	}
}
