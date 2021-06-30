package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class FoodCard implements Card {

	@Override
	public Consumer buy(ExtractDTO param, Consumer consumer, ConsumerRepository repository) {
		// Para compras no cartão de alimentação o cliente recebe um desconto de 10%
		Double cashback = (param.getValue() / 100) * 10;
		param.setValue(param.getValue() - cashback);

		consumer = repository.findByFoodCardNumber(param.getId(), param.getCardNumber());

		if (consumer != null) {
			consumer.setFoodCardBalance(consumer.getFoodCardBalance() - param.getValue());
			repository.save(consumer);
		}

		return consumer;
	}

}
