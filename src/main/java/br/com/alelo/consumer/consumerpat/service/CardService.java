package br.com.alelo.consumer.consumerpat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.error.EntityNotFoundException;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	
	public void save(Card card) {
		cardRepository.save(card);
	}
	
	public Card findByCardNumber(Long cardNumber) {
		return Optional.ofNullable(cardRepository.findByCardNumber(cardNumber))//
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Card com cardNumber %s não encontrado", cardNumber)));
	}

}
