package br.com.alelo.consumer.consumerpat.service;

import java.util.List;

import javax.validation.Valid;

import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;

public interface ConsumerService {

	List<Consumer> findAllConsumers();

	void convertAndSaveConsumer(@Valid ConsumerDTO consumerDTO);
}
