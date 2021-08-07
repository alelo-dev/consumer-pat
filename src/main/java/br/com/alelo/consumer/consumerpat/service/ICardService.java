package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.CardBalanceDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;

@Service
public interface ICardService {
	
	
	
	/**
	 * Método responsável por realizar uma busca de um Card, através do cardNumber.
	 * 
	 * @param number
	 * @return {@link Card}
	 */
	Card findByNumber(String number);

	/**
	 * Método responsável por creditar um valor ao saldo do cartão.
	 * 
	 * @param cardBalanceDTO
	 */
	Card setCardBalance(CardBalanceDTO cardBalanceDTO);
	
	/**
	 * Método responsável por debitar um valor ao saldo do cartão.
	 * 
	 * @param cardBalanceDTO
	 */
	Card buyCardBalance(String cardNumber, BigDecimal value);

}
