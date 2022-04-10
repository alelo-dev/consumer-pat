package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.BalanceException;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.utils.UUIDUtils;

@Service
public class CardService implements ICardService{

	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	EstablishmentRepository establishmentRepository;
	
	@Override
	public Card validationBuyCard(BuyDto buyDto) throws Exception {
		
		Optional<Card> cardOptional = cardRepository.findByCardNumber(buyDto.getCardDto().getCardNumber());
		
		if (!verifyIsCard(cardOptional)) {
			throw new CardNotFoundException("Card "+buyDto.getCardDto().getCardNumber()+" not found!");
		}
		
		if (!verifyIsEstablishment(buyDto)) {
			throw new EstablishmentNotFoundException("Establishiment not found!");
		}
		
		if (!verifyCardIsType(cardOptional, buyDto)) {
			throw new CardTypeNotFoundException("CardType not found!");
		}
			
		if (!verifyIsBalance(cardOptional, buyDto.getValue())) {
			throw new BalanceException("No sufficient or 0 balance!");
		}
		
		return cardOptional.get();
	}
	
	@Override
	public Boolean verifyIsCard(Optional<Card> cardOptional) {
		return cardOptional.isPresent();
	}
	
	@Override
    public boolean verifyIsEstablishment(BuyDto buyDto) {
        return establishmentRepository.findById(UUIDUtils.makeUuid(buyDto.getIdEstablishment())).isPresent();
    }
	
	@Override
    public Boolean verifyCardIsType(Optional<Card> cardOptional, BuyDto buyDto) {
        return cardOptional.get().getTypeCard().getIdTypeCard().equals(buyDto.getCardDto().getTypeCard().getIdTypeCard());
    }
    
	@Override
	public Boolean verifyIsBalance(Optional<Card> cardOptional, BigDecimal value) {
		return isBalance(cardOptional, value);
	}
    
	@Override
	public Card debitBalance(Card card, BigDecimal value) {
		card.addBalance(card.getCardBalance().subtract(value));
		return cardRepository.save(card);
	}
	
	@Override
	public Card creditBalance(Card card, BigDecimal value) {
		card.addBalance(card.getCardBalance().add(value));
		return cardRepository.save(card);
	}
	    
    public Boolean isBalance(Optional<Card> cardOptional, BigDecimal value){
        if(cardOptional.get().getCardBalance().subtract(value).compareTo(BigDecimal.ZERO) > 0){
            return true;
        }
        return false;
    }


}
