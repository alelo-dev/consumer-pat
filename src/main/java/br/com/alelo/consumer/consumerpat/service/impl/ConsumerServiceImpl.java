package br.com.alelo.consumer.consumerpat.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.service.dto.request.AddressRequest;
import br.com.alelo.consumer.consumerpat.service.dto.request.CardsRequest;
import br.com.alelo.consumer.consumerpat.service.dto.request.ConsumerRequest;
import br.com.alelo.consumer.consumerpat.service.dto.request.ContactRequest;
import br.com.alelo.consumer.consumerpat.service.dto.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.mapper.EntityMapper;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private EntityMapper<ConsumerRequest, Consumer> consumerRequestMapper;

	@Autowired
	private EntityMapper<Consumer, ConsumerResponse> consumerResponseMapper;

	@Override
	public ConsumerResponse save(ConsumerRequest request) {

		Consumer consumer = consumerRequestMapper.map(request);
		consumer = consumerRepository.save(consumer);
		return consumerResponseMapper.map(consumer);
	}

	@Override
	public List<ConsumerResponse> findAll() {

		List<Consumer> consumers = consumerRepository.findAll();
		return consumers.stream().map(consumerResponseMapper::map).collect(Collectors.toList());
	}

	@Override
	public Optional<ConsumerResponse> findOne(Long id) {

		return consumerRepository.findById(id).map(consumerResponseMapper::map);
	}

	@Override
	public ConsumerResponse update(Long id, ConsumerRequest request) {

		Optional<Consumer> optional = consumerRepository.findById(id);
		if (!optional.isPresent()) {
			throw new RuntimeException("Consumer not found by id");
		}
		changeCardBalance(request, optional.get());
		
		AddressRequest addressRequest = request.getAddressRequest();
		addressRequest.setId(optional.get().getAddress().getId());
		request.setAddressRequest(addressRequest);
		
		CardsRequest cardsRequest = request.getCardsRequest();
		cardsRequest.setId(optional.get().getCards().getId());
		request.setCardsRequest(cardsRequest);
		
		ContactRequest contactRequest = request.getContactRequest();
		contactRequest.setId(optional.get().getContact().getId());
		request.setContactRequest(contactRequest);
		
		Consumer consumer = consumerRequestMapper.map(request);
		consumer.setId(optional.get().getId());
		consumer = consumerRepository.save(consumer);
		return consumerResponseMapper.map(consumer);
	}

	@Override
	public void delete(Long id) {

		Optional<Consumer> optional = consumerRepository.findById(id);
		if (!optional.isPresent()) {
			throw new RuntimeException("Consumer not found by id");
		}

		consumerRepository.deleteById(id);
	}

	private void changeCardBalance(ConsumerRequest request, Consumer consumer) {

		if (request.getCardsRequest().getDrugstoreCardBalance() != consumer.getCards().getDrugstoreCardBalance()
				|| request.getCardsRequest().getFoodCardBalance() != consumer.getCards().getFoodCardBalance()
				|| request.getCardsRequest().getFuelCardBalance() != consumer.getCards().getFuelCardBalance()) {
			throw new RuntimeException(
					"It is not possible to change the card balance, it is necessary to credit an amount");
		}
	}
}
