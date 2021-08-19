package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;

public interface ConsumerService {
	
	List<Consumer> listAllConsumers();
	
    Consumer createConsumer(Consumer consumer);
	
    Consumer updateConsumer(Consumer consumer);
	

}
