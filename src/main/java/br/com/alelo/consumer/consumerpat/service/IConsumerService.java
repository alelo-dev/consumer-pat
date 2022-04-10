package br.com.alelo.consumer.consumerpat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.entity.Consumer;

public interface IConsumerService {

	public Consumer getConsumer(String id);
	
	public Page<Consumer> getAllConsumers(Pageable pageable);

	public Consumer createConsumer(ConsumerDto consumer) throws Exception;

	public Consumer updateConsumer(ConsumerDto consumer);

}
