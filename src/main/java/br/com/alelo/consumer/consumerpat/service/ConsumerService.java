package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerService {

	void createConsumer(Consumer consumer);

	void updateConsumer(Consumer consumer);

	List<Consumer> listAllConsumers();

}
