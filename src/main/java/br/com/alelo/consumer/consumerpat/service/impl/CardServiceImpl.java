package br.com.alelo.consumer.consumerpat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.ICardService;

@Service
public class CardServiceImpl implements ICardService {

	@Autowired
	CardRepository repository;
	
	@Override
	public void setCardBalance(CardBalanceDTO cardBalanceDTO) {
		Card card = repository
				.findByNumber(cardBalanceDTO.getCardNumber())
				.orElseThrow(() -> new CardNotFoundException(cardBalanceDTO.getCardNumber()));
		
		card.setBalance(card.getBalance().add(cardBalanceDTO.getValue()));
		repository.save(card);
	}

}
