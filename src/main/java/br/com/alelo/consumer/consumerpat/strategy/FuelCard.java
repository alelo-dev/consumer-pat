package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class FuelCard implements Card {

	@Override
	public Consumer buy(ExtractDTO param, Consumer consumer, ConsumerRepository repository) {
		// Nas compras com o cartão de combustivel existe um acrescimo de 35%;
		Double tax = (param.getValue() / 100) * 35;
		param.setValue(param.getValue() + tax);

		consumer = repository.findByFuelCardNumber(param.getId(), param.getCardNumber());

		if (consumer != null) {
			consumer.setFuelCardBalance(consumer.getFuelCardBalance() - param.getValue());
			repository.save(consumer);
		}
		
		return consumer;
	}

}
