package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerService {

	void setBalance(int cardNumber, double value);

	void buy(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value);

	List<Consumer> listAllConsumers();

	Consumer createOrUpdateConsumer(Consumer consumer);
	
	Consumer updateConsumer(Consumer consumer);

}