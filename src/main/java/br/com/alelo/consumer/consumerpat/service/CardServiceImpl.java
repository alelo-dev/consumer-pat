package br.com.alelo.consumer.consumerpat.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.converter.CardConsumerConverter;
import br.com.alelo.consumer.consumerpat.dto.CardConsumerRequestDTO;
import br.com.alelo.consumer.consumerpat.dto.ChargeRequestDTO;
import br.com.alelo.consumer.consumerpat.entity.CardConsumer;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.RestNotFoundException;
import br.com.alelo.consumer.consumerpat.repository.CardConsumerRepository;
import br.com.alelo.consumer.consumerpat.repository.ConsumerRepository;

@Service
public class CardServiceImpl implements ICardService{


	@Autowired
	CardConsumerRepository cardConsumerRepository;

	@Autowired
	ConsumerRepository consumerRepository;

	public CardConsumer findByNumber(final String cardNumber) {

		CardConsumer cardConsumer = cardConsumerRepository.findByCardNumber(cardNumber);

		if(cardConsumer != null) {
			return cardConsumer;
		}else {
			throw new RestNotFoundException("CardConsumer not found");
		}
	}


	public CardConsumer findByNumberAndConsumer(final String cardNumber, final Long consumerId) {

		CardConsumer cardConsumer = cardConsumerRepository.findByNumberAndConsumer(cardNumber, consumerId);

		if(cardConsumer != null) {
			return cardConsumer;
		}else {
			throw new RestNotFoundException("CardConsumer not found");
		}
	}


	public void chargeCardConsumer(Long consumerId, ChargeRequestDTO chargeDTO) {
		
		CardConsumer cardConsumer = findByNumberAndConsumer(chargeDTO.getCardNumber(), consumerId);

		if (chargeDTO.getValue().compareTo(0d) < 0) {
			throw new IllegalArgumentException("Negative value");
		}
		cardConsumer.setBalance(cardConsumer.getBalance() + chargeDTO.getValue());
		cardConsumer.setUpdatedAt(LocalDateTime.now());
		cardConsumerRepository.save(cardConsumer);
	}


	@Override
	public void addCardconsumer(Consumer consumer, CardConsumerRequestDTO cardConsumerRequestDTO) {
		// TODO Auto-generated method stub

		CardConsumer cardConsumer = CardConsumerConverter.toEntity(cardConsumerRequestDTO);
		cardConsumer.setConsumer(consumer);

		try {
			cardConsumerRepository.save(cardConsumer);
		}catch(DataIntegrityViolationException ex) {
			throw new RestNotFoundException("CardConsumer number in use");
		}
	}
}
