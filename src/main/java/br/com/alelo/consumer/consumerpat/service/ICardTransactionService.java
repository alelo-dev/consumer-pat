package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CardTransactionDTO;
import br.com.alelo.consumer.consumerpat.dto.ConsumerDTO;

@Service
public interface ICardTransactionService {
	

	/**
	 * Método responsável por realizar a consulta paginada de todos as transações de um cartão.
	 * 
	 * @param page, size
	 * @return {@link Page} of {@link ConsumerDTO}
	 */
	Page<CardTransactionDTO> pageTransactions(Integer page, Integer size, String cardNumber);

	/**
	 * Método responsável por realizar o cadastro de 
	 * histórico de creditos para uma transação do cartão.
	 * 
	 * @param cardNumber
	 * @param value
	 * @param balance
	 */
	void creditTransaction(String cardNumber, BigDecimal value, BigDecimal balance);
	
	/**
	 * Método responsável por realizar o cadastro de 
	 * histórico de debitos para uma transação do cartão.
	 * 
	 * @param cardNumber
	 * @param value
	 * @param balance
	 */
	void debitTransaction(String cardNumber, BigDecimal value, BigDecimal balance);

}
