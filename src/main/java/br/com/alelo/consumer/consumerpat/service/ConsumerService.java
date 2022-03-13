package br.com.alelo.consumer.consumerpat.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;

public interface ConsumerService {

	Page<Consumer> findAllConsumers(Pageable pageable);

	Consumer convertAndSaveConsumer(@Valid ConsumerDTO consumerDTO);

	Consumer updateConsumer(@Valid ConsumerDTO consumerDTO, Long consumerId);
	
	Consumer findConsumer(Long id);
	
}
