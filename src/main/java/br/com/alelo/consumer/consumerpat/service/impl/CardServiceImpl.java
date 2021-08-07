package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.NotEnoughBalanceException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.ICardService;

@Service
public class CardServiceImpl implements ICardService {

	@Autowired
	CardRepository repository;
	
	@Autowired
	CardTransactionService cardTransactionService;
	

	@Override
	public Card findByNumber(String number) {
		return repository.findByNumber(number).orElseThrow(() -> new CardNotFoundException(number));
	}
	
	@Override
	public void setCardBalance(CardBalanceDTO cardBalanceDTO) {
		Card card = findByNumber(cardBalanceDTO.getCardNumber());
		card.setBalance(card.getBalance().add(cardBalanceDTO.getValue()));
		cardTransactionService.creditTransaction(card.getNumber(), cardBalanceDTO.getValue(), card.getBalance());
		repository.save(card);
	}

	@Override
	public void buyCardBalance(String cardNumber, BigDecimal value) {
		Card card = findByNumber(cardNumber);
		validations(value, card);
		card.setBalance(card.getBalance().subtract(value));
		cardTransactionService.debitTransaction(cardNumber, value, card.getBalance());
		repository.save(card);
	}

	/**
	 * Método responsável por realizar as validações.
	 * 
	 * @param value
	 * @param card
	 */
	private void validations(BigDecimal value, Card card) {
		if (card.getBalance().subtract(value).signum() < 0) {			
			throw new NotEnoughBalanceException(card.getBalance());
		}
	}

}
