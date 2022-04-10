package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;

import br.com.alelo.consumer.consumerpat.dto.AddBalanceDto;
import br.com.alelo.consumer.consumerpat.dto.BalanceCardDto;
import br.com.alelo.consumer.consumerpat.dto.NewCardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;

public interface ICardService {

	Card createNewCard(NewCardDto newCardDto) throws Exception;

	Card recharge(AddBalanceDto addBalanceDto) throws Exception;

	ResponseEntity<String> deleteCard(String id) throws Exception;

	Card checkBalance(BalanceCardDto balanceCardDto) throws Exception;

	Card debitBalance(Card card, BigDecimal value);

	Card creditBalance(Card card, BigDecimal value);

	

}
