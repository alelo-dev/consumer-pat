package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.BalanceCardDto;
import br.com.alelo.consumer.consumerpat.dto.BuyDto;
import br.com.alelo.consumer.consumerpat.dto.NewCardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.TypeCard;
import br.com.alelo.consumer.consumerpat.exception.BalanceException;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.EstablishmentNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.UserNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.EstablishmentRepository;
import br.com.alelo.consumer.consumerpat.respository.TypeCardRepository;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.utils.UUIDUtils;

@Service
public class CardService implements ICardService{

	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	EstablishmentRepository establishmentRepository;
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	TypeCardRepository typeCardRepository;
	
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
	    
	@Override
    public Boolean isBalance(Optional<Card> cardOptional, BigDecimal value){
        if(cardOptional.get().getCardBalance().subtract(value).compareTo(BigDecimal.ZERO) > 0){
            return true;
        }
        return false;
    }

	@Override
	public ResponseEntity<String> deleteCard(String id) throws Exception {
		
		Optional<Card> cardOptional = cardRepository.findById(UUIDUtils.makeUuid(id));
		if (!cardOptional.isPresent()) {
			throw new CardNotFoundException("Card not found!");
		}
		cardRepository.delete(cardOptional.get());
		
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

	public Card createNewCard(NewCardDto newCardDto) throws Exception {
		
		Optional<Consumer> consumerOptional = consumerRepository.findById(UUIDUtils.makeUuid(newCardDto.getIdConsumer()));
		if (!consumerOptional.isPresent()) {
			throw new UserNotFoundException("User not found!");
		}
		
		Card card = new Card();
		card.addBalance(newCardDto.getInitialBalanace());
		card.setCardNumber(Long.valueOf(generateCardNumber(100000, 9999999)));
		
		Optional<TypeCard> findByTypeCard = typeCardRepository.findById(newCardDto.getTypeCard().getIdTypeCard());
		if (!findByTypeCard.isPresent()) {
			throw new CardTypeNotFoundException("Card type not found!");
		}
		card.setTypeCard(findByTypeCard.get());
		Card savedCard = cardRepository.save(card);
		
		List<Card> cards = consumerOptional.get().getCards();
		cards.add(savedCard);
		consumerOptional.get().setCards(cards);
		consumerRepository.save(consumerOptional.get());

		return savedCard;
	}
	
    public static String generateCardNumber(int min, int max) {
        Random r = new Random();
        Integer value = r.nextInt((max - min) + 1) + min;
        return value.toString();
    }

	public Card checkBalance(BalanceCardDto balanceCardDto) throws Exception {
		
		Optional<Consumer> consumerOptional = consumerRepository.findById(UUIDUtils.makeUuid(balanceCardDto.getIdConsumer()));
		if (!consumerOptional.isPresent()) {
			throw new UserNotFoundException("User not found!");
		}
		
		List<Card> cards = consumerOptional.get().getCards();
		Card card = cards.stream()
				  .filter(c -> UUIDUtils.makeUuid(balanceCardDto.getIdCard()).equals(c.getIdCard()))
				  .findAny()
				  .orElseThrow( () -> new CardNotFoundException("Card not found!"));

		return card;
	}


}
