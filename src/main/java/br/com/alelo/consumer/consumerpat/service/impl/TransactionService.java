package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.AddBalanceDto;
import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.ExtractDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	CardService cardService;

	@Autowired
	ExtractService extractService;

	private static final Integer FOOD = 1;
	private static final Integer FUEL = 2;
	private static final Integer DRUGSTORE = 2;
	
	@Override
	public ExtractDto buy(BuyDto buyDto) throws Exception {
		
		Card card = cardService.validationBuyCard(buyDto);

		if(card.getTypeCard().getIdTypeCard().equals(FOOD)) {
	        // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
	        BigDecimal cashback  = buyDto.getValue().divide(BigDecimal.valueOf(100L)).multiply(BigDecimal.TEN);
	        buyDto.setValue(buyDto.getValue().subtract(cashback));
	        cardService.debitBalance(card, buyDto.getValue());
		}else if(card.getTypeCard().getIdTypeCard().equals(FUEL)) {
	        // Nas compras com o cartão de combustivel existe um acrescimo de 35%;
	        BigDecimal tax  = buyDto.getValue().divide(BigDecimal.valueOf(100L)).multiply(BigDecimal.valueOf(35L));
	        buyDto.setValue(buyDto.getValue().add(tax));
	        cardService.debitBalance(card, buyDto.getValue());
		}else if(card.getTypeCard().getIdTypeCard().equals(DRUGSTORE)) {
			 cardService.debitBalance(card, buyDto.getValue());
		}

		return extractService.saveExtract(buyDto);
	}

	@Override
	public List<ExtractDto> consultExtract() {
		
		//TODO consult extratic
		return null;
	}

	public Card addBalance(AddBalanceDto addBalanceDto) throws Exception {
		
		Optional<Card> cardOptional = cardRepository.findByCardNumber(addBalanceDto.getCardNumber());
		
		if (!cardService.verifyIsCard(cardOptional)) {
			throw new CardNotFoundException("Card "+addBalanceDto.getCardNumber()+" not found!");
		}

		return cardService.creditBalance(cardOptional.get(), addBalanceDto.getValue());
	}
	

}
