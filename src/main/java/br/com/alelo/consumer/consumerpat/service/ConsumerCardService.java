package br.com.alelo.consumer.consumerpat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.exception.ConsumerCardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.ConsumerNotFoundException;
import br.com.alelo.consumer.consumerpat.model.converter.ConsumerCardConverter;
import br.com.alelo.consumer.consumerpat.model.dto.ConsumerCardDTO;
import br.com.alelo.consumer.consumerpat.model.entity.ConsumerCard;
import br.com.alelo.consumer.consumerpat.respository.ConsumerCardRepository;

@Service
public class ConsumerCardService {
	
	@Autowired
	private ConsumerCardRepository consumerCardRepository;
	
	public ConsumerCard findById(final Long id){
		return this.consumerCardRepository.findById(id).orElseThrow(() -> new ConsumerNotFoundException());
	}
	
	public ConsumerCard findByCardNumber(final String cardNumber){
		return this.consumerCardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new ConsumerCardNotFoundException());
	}
	
	public void save(final ConsumerCardDTO dto){
		this.consumerCardRepository.save(ConsumerCardConverter.toEntity(dto));
	}

	public void update(final Long id, final ConsumerCardDTO dto){
		final ConsumerCard consumerCard = this.findById(id);
		consumerCard.setCardBalance(dto.getCardBalance());
		this.consumerCardRepository.save(consumerCard);
	}

}