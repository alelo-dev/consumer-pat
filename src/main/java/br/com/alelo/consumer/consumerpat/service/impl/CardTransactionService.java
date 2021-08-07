package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CardTransactionDTO;
import br.com.alelo.consumer.consumerpat.entity.CardTransaction;
import br.com.alelo.consumer.consumerpat.enuns.CardOperationEnum;
import br.com.alelo.consumer.consumerpat.respository.CardTransactionRepository;
import br.com.alelo.consumer.consumerpat.service.ICardTransactionService;

@Service
public class CardTransactionService implements ICardTransactionService {

	@Autowired
	CardTransactionRepository repository;
	
	@Override
	public void creditTransaction(String cardNumber, BigDecimal value, BigDecimal balance) {
		repository.save(new CardTransaction(null, cardNumber, CardOperationEnum.CREDIT, value, balance, LocalDateTime.now()));
	}

	@Override
	public void debitTransaction(String cardNumber, BigDecimal value, BigDecimal balance) {
		repository.save(new CardTransaction(null, cardNumber, CardOperationEnum.DEBIT, value, balance, LocalDateTime.now()));
	}

	@Override
	public Page<CardTransactionDTO> pageTransactions(Integer page, Integer size, String cardNumber) {
		Page<CardTransaction> listConsumers = repository.findAllByCardNumber(cardNumber, PageRequest.of(page, size));
		return listConsumers.map(CardTransactionDTO::to);
	}

}
