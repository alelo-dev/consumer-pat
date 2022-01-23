package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.request.BuyRequest;
import br.com.alelo.consumer.consumerpat.request.UpdateBalanceRequest;

public interface ConsumerService {
	
	Consumer updateBalance(UpdateBalanceRequest updateBalance);
	
	Page<Consumer> getAllConsumersList(Pageable pageable);
	
	boolean registerConsumer(Consumer consumer);
	
	boolean updateConsumer(Consumer consumer);
	
	void buy(BuyRequest buyRequest);

}
