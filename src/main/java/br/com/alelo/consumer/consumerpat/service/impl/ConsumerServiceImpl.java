package br.com.alelo.consumer.consumerpat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.ConsumerCreateDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.IConsumerService;

@Service
public class ConsumerServiceImpl implements IConsumerService {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	ConsumerRepository repository;

	@Override
	public Page<ConsumerDTO> pageConsumers(Integer page, Integer size) {
		Page<Consumer> listConsumers = repository.findAll(PageRequest.of(page, size));
		return listConsumers.map(ConsumerDTO::to);
	}
	
	@Override
	public ConsumerDTO findById(Long id) {
		return ConsumerDTO.to(repository.getOne(id));
	}

	@Override
	public Consumer create(ConsumerCreateDTO consumerCreateDTO) {
		Consumer consumer = consumerCreateDTO.toEntity();
		consumer.getAddress().forEach(a -> a.setConsumer(consumer));
		consumer.getCards().forEach(c -> c.setConsumer(consumer));
		consumer.getContacts().forEach(c -> c.setConsumer(consumer));
		return repository.save(consumer);
	}

	@Override
	public Consumer update(ConsumerDTO consumerDTO) {
		Consumer consumer = consumerDTO.toEntity();
		consumer.getAddress().forEach(a -> a.setConsumer(consumer));
		consumer.getContacts().forEach(c -> c.setConsumer(consumer));
		consumer.getCards().forEach(c -> c.setConsumer(consumer));
/**
 * Caso seja necessário alterar os dados do cartão como o tipo e o numero, 
 * ativiar este trecho e inativar linha acima do codigo. 
 * Também é necessário alterar a entidade Consumers o CascadeType, do atributo cards.
 **/
//		consumer.getCards().forEach(c -> {
//			c.setConsumer(consumer);
//			if (!ObjectUtils.isEmpty(c.getId())) {				
//				c.setBalance(cardRepository.findBalanceByCardId(c.getId()));
//			}
//		}); 
		return repository.save(consumer);
	}


}
