package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface IConsumerService {
	
	public List<Consumer> getAllConsumersList();
	
	public Consumer createConsumer(Consumer consumer);
	
	public Consumer updateConsumer(Consumer consumer);
	
	public Consumer setBalance(int cardNumber, double value);


}
