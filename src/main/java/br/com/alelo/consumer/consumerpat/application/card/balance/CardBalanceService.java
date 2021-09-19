package br.com.alelo.consumer.consumerpat.application.card.balance;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.application.card.balance.response.CardBalanceResponse;
import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.infrastructure.exception.types.NotFoundException;
import br.com.alelo.consumer.consumerpat.infrastructure.respository.CardRepository;

@Service
public class CardBalanceService {

	@Autowired
	private CardRepository cardRepository;

	public CardBalanceResponse setBalance(Long cardNumber, BigDecimal value) {

		Optional<Card> findById = cardRepository.findById(cardNumber);

		if (findById.isEmpty()) {
			throw new NotFoundException("card not found");
		}

		Card card = findById.get();
		card.addFounds(value);
		cardRepository.save(card);
		return new CardBalanceResponse(card.getNumber(), card.getBalance());
	}
}
