package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import br.com.alelo.consumer.consumerpat.error.BusinessError;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.dto.CardVO;
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
	public Page<Consumer> findAllConsumers(Pageable pageable) throws BusinessException {
		Page<Consumer> pageConsumer = consumerRepository.findAll(pageable);

		if (!pageConsumer.hasContent()) {
			throw new BusinessException(BusinessError.RESOURCE_NOT_FOUND);
		}

		return pageConsumer;
	}

	@Override
	@Transactional
	public Consumer convertAndSaveConsumer(ConsumerDTO consumerDTO) {

		Consumer consumer = Consumer.builder().name(consumerDTO.getName())
				.documentNumber(consumerDTO.getDocumentNumber()).birthDate(consumerDTO.getBirthDate())
				.contact(contactMapper.voToModel(consumerDTO.getContact()))
				.adress(adressMapper.voToModel(consumerDTO.getAdress()))
				.cards(consumerDTO.getCards().stream().map(cardMapper::voToModel).collect(Collectors.toList())).build();

		return consumerRepository.save(consumer);
	}

	@Override
	@Transactional
	public Consumer updateConsumer(@Valid ConsumerDTO consumerDTO, Long consumerId) {
		Consumer consumer = findConsumer(consumerId);

		consumer.setName(consumerDTO.getName());
		consumer.setDocumentNumber(consumerDTO.getDocumentNumber());
		consumer.setBirthDate(consumerDTO.getBirthDate());
		consumer.setContact(contactMapper.voToModel(consumerDTO.getContact()));
		consumer.setAdress(adressMapper.voToModel(consumerDTO.getAdress()));

		List<Card> newCards = consumerDTO.getCards().stream().map(cardMapper::voToModel).collect(Collectors.toList());

		List<Integer> cardNumbers = consumer.getCards().stream().map(Card::getCardNumber).collect(Collectors.toList());

		for (Card card : newCards) {
			if (!cardNumbers.contains(card.getCardNumber())) {
				consumer.getCards().add(card);
			} else {
				consumer.getCards().stream().filter(c -> c.getCardNumber().equals(card.getCardNumber())).findAny()
						.ifPresent(c -> c.setTypeCard(card.getTypeCard()));
			}
		}

		return consumer;
	}

	@Override
	@Transactional
	public Consumer findConsumer(Long id) throws BusinessException {
		return consumerRepository.findById(id)
				.orElseThrow(() -> new BusinessException(BusinessError.RESOURCE_NOT_FOUND));
	}

}
