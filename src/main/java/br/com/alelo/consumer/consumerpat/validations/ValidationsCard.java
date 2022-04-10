package br.com.alelo.consumer.consumerpat.validations;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.BalanceException;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotAcceptCardException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;

public class ValidationsCard {
	

	public static Card validationBuyCard(BuyDto buyDto, CardRepository cardRepository, EstablishmentRepository establishmentRepository) throws Exception {
		
		Optional<Card> cardOptional = cardRepository.findByCardNumber(buyDto.getCardDto().getCardNumber());
		
		if (!verifyIsCard(cardOptional)) {
			throw new CardNotFoundException("Card "+buyDto.getCardDto().getCardNumber()+" not found!");
		}
		
		if (!ValidationsEstablishment.verifyIsEstablishment(buyDto, establishmentRepository)) {
			throw new EstablishmentNotFoundException("Establishiment not found!");
		}
		
		if (!ValidationsEstablishment.verifyIsEstablishmentCompatibleWithCard(buyDto, establishmentRepository, cardRepository)) {
			throw new EstablishmentNotAcceptCardException("Establishiment not accept yout card: "+buyDto.getCardDto().getTypeCard());
		}

		if (!verifyCardIsType(cardOptional, buyDto)) {
			throw new CardTypeNotFoundException("CardType not found!");
		}
			
		if (!verifyIsBalance(cardOptional, buyDto.getValue())) {
			throw new BalanceException("No sufficient or 0 balance!");
		}
	
		return cardOptional.get();
	}
	
	public static boolean verifyIsCard(Optional<Card> cardOptional) {
		return cardOptional.isPresent();
	}
	

    public static boolean verifyCardIsType(Optional<Card> cardOptional, BuyDto buyDto) {
        return cardOptional.get().getTypeCard().getIdTypeCard().equals(buyDto.getCardDto().getTypeCard().getIdTypeCard());
    }
    
	public static boolean verifyIsBalance(Optional<Card> cardOptional, BigDecimal value) {
		return isBalance(cardOptional, value);
	}
	
    public static boolean isBalance(Optional<Card> cardOptional, BigDecimal value){
        if(cardOptional.get().getCardBalance().subtract(value).compareTo(BigDecimal.ZERO) > 0){
            return true;
        }
        return false;
    }

}
