package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.ExtractRepository;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;

@Service
public class ConsumerService implements IConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	ExtractRepository extractRepository;

	/* Deve listar todos os clientes (cerca de 500) */
	@Override
	public List<Consumer> getAllConsumersList() {
		return consumerRepository.findAll();
	}

	@Override
	public Consumer createConsumer(Consumer consumer) {
		return consumerRepository.save(consumer);
	}

	// Não deve ser possível alterar o saldo do cartão
	@Override
	public Consumer updateConsumer(Consumer consumer) {
		return consumerRepository.save(consumer);

	}


}
