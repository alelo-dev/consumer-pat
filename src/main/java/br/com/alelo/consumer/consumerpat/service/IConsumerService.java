package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface IConsumerService {
	
	public List<Consumer> getAllConsumersList();
	
	public void createConsumer(Consumer consumer);
	
	public void updateConsumer(Consumer consumer);
	
	public void setBalance(int cardNumber, double value);
	
	public void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value);

}
