package br.com.alelo.consumer.consumerpat.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.entity.Card;

public interface ICardService {

	public Card validationBuyCard(BuyDto buyDto) throws Exception;

	public Boolean verifyIsCard(Optional<Card> cardOptional);

	public boolean verifyIsEstablishment(BuyDto buyDto);

	public Boolean verifyCardIsType(Optional<Card> cardOptional, BuyDto buyDto);

	public Boolean verifyIsBalance(Optional<Card> cardOptional, BigDecimal value);

	public Card debitBalance(Card card, BigDecimal value);

	public Card creditBalance(Card card, BigDecimal value);

	Boolean isBalance(Optional<Card> cardOptional, BigDecimal value);

	ResponseEntity<String> deleteCard(String id) throws Exception;

}
