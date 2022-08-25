package br.com.alelo.consumer.consumerpat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerDto;
import br.com.alelo.consumer.consumerpat.dto.MapConsumerDto;
import br.com.alelo.consumer.consumerpat.error.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;	

	@Autowired
	private MapConsumerDto mapConsumerDto;

	public void save(ConsumerDto consumerDto) {
		consumerRepository.save(mapConsumerDto.create(consumerDto));
	}

	public Page<Consumer> findAll(int page, int size) {
		return consumerRepository.findAll(PageRequest.of(page - 1, size));
	}

	public void update(Long id, ConsumerDto consumerDto) {
		Optional<Consumer> consumerOp = consumerRepository.findById(id);
		if (consumerOp.isPresent()) {
			Consumer consumer = consumerOp.get();
			mapConsumerDto.update(consumerDto, consumer);
			consumerRepository.save(consumer);
		} else {
			throw new EntityNotFoundException(String.format("Consumer com id %s não encontrado", id));
		}
	}

}
