package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface ConsumerService {
	
	Consumer updateBalance(Integer cardNumber, Double value);
	
	Page<Consumer> getAllConsumersList(Pageable pageable);
	
	boolean registerConsumer(Consumer consumer);
	
	boolean updateConsumer(Consumer consumer);
	
	void buy(Integer establishmentType, String establishmentName, Integer cardNumber, String productDescription, Double value);

}
