package br.com.alelo.consumer.consumerpat.respository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.interfaces.IConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.jpa.ConsumerJPA;

@Repository
public class ConsumerRepository implements IConsumerRepository{
	
	@Autowired
	private ConsumerJPA consumerJPA;

	@Override
	public List<Consumer> getAllConsumersList() {		
		return consumerJPA.getAllConsumersList();
	}

	@Override
	public Consumer findByFoodCardNumber(int cardNumber) {		
		return consumerJPA.findByFoodCardNumber(cardNumber);
	}

	@Override
	public Consumer findByFuelCardNumber(int cardNumber) {		
		return consumerJPA.findByFuelCardNumber(cardNumber);
	}

	@Override
	public Consumer findByDrugstoreNumber(int cardNumber) {		
		return consumerJPA.findByDrugstoreNumber(cardNumber);
	}

	@Override
	public void gravarConsumer(Consumer consumer) {
		consumerJPA.save(consumer);
		
	}

    
}
