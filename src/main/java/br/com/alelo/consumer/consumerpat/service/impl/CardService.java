package br.com.alelo.consumer.consumerpat.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.alelo.consumer.consumerpat.dto.AddBalanceDto;
import br.com.alelo.consumer.consumerpat.dto.BalanceCardDto;
import br.com.alelo.consumer.consumerpat.dto.NewCardDto;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.entity.TypeCard;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardTypeNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.UserNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.respository.TypeCardRepository;
import br.com.alelo.consumer.consumerpat.service.ICardService;
import br.com.alelo.consumer.consumerpat.utils.UUIDUtils;
import br.com.alelo.consumer.consumerpat.validations.ValidationsCard;

@Service
public class CardService implements ICardService{

	@Autowired
	CardRepository cardRepository;
		
	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	TypeCardRepository typeCardRepository;
	
	
	@Override
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
	
	@Override
	public Card recharge(AddBalanceDto addBalanceDto) throws Exception {
		
		Optional<Card> cardOptional = cardRepository.findByCardNumber(addBalanceDto.getCardNumber());
		
		if (!ValidationsCard.verifyIsCard(cardOptional)) {
			throw new CardNotFoundException("Card "+addBalanceDto.getCardNumber()+" not found!");
		}

		return creditBalance(cardOptional.get(), addBalanceDto.getValue());
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
	
    @Override
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
	    
    public static String generateCardNumber(int min, int max) {
        Random r = new Random();
        Integer value = r.nextInt((max - min) + 1) + min;
        return value.toString();
    }

}
