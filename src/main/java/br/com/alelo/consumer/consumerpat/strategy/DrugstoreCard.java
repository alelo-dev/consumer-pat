package br.com.alelo.consumer.consumerpat.strategy;

import br.com.alelo.consumer.consumerpat.dto.ExtractDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

public class DrugstoreCard implements Card {

	@Override
	public Consumer buy(ExtractDTO param, Consumer consumer, ConsumerRepository repository) {
		consumer = repository.findByDrugstoreNumber(param.getId(), param.getCardNumber());

		if (consumer != null) {
			consumer.setDrugstoreCardBalance(consumer.getDrugstoreCardBalance() - param.getValue());
			repository.save(consumer);
		}
		
		return consumer;
	}

}
