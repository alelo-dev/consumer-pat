package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public List<Consumer> listAllConsumers() {
		return consumerRepository.findAll();
	}

	@Override
	public Consumer createConsumer(Consumer consumer) {
		return consumerRepository.save(consumer);
	}

	@Override
	public Consumer updateConsumer(Consumer consumer) {
		return consumerRepository.save(consumer);
	}

}
