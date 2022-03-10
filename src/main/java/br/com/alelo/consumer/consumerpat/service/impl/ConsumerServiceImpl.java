package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.error.BusinessError;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.Consumer.ConsumerBuilder;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.model.mapper.AdressMapper;
import br.com.alelo.consumer.consumerpat.model.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.model.mapper.ContactMapper;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	private ConsumerRepository consumerRepository;

	private ContactMapper contactMapper;

	private AdressMapper adressMapper;

	private CardMapper cardMapper;

	@Autowired
	public ConsumerServiceImpl(ConsumerRepository consumerRepository, ContactMapper contactMapper,
			AdressMapper adressMapper, CardMapper cardMapper) {
		this.consumerRepository = consumerRepository;
		this.contactMapper = contactMapper;
		this.adressMapper = adressMapper;
		this.cardMapper = cardMapper;
	}

	@Override
	@Transactional
	public List<Consumer> findAllConsumers() throws BusinessException {
		List<Consumer> consumers = consumerRepository.findAll();

		if (consumers.isEmpty()) {
			throw new BusinessException(BusinessError.RESOURCE_NOT_FOUND);
		}

		return consumers;
	}

	@Override
	@Transactional
	public void convertAndSaveConsumer(ConsumerDTO consumerDTO) {

		Consumer consumer = Consumer.builder()
			.name(consumerDTO.getName())
			.documentNumber(consumerDTO.getDocumentNumber())
			.birthDate(consumerDTO.getBirthDate())
			.contact(contactMapper.voToModel(consumerDTO.getContact()))
			.adress(adressMapper.voToModel(consumerDTO.getAdress()))
			.cards(consumerDTO.getCards().stream().map(cardMapper::voToModel).collect(Collectors.toList())).build();
			
		consumerRepository.save(consumer);
	}

}
