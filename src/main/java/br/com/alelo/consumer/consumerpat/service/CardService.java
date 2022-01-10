package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.dto.CardBuyRequest;
import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository repository;


    public Boolean debitBalance(Long cardNumber, BigDecimal value){
        Optional<Card> cardOptional = repository.findByCardNumber(cardNumber);
        if(cardOptional.isPresent()){
            Card card = cardOptional.get();
            card.setAddBalance(value);
            repository.save(card);
            return  true;
        }
        return false;
    }

    public void creditBalance(Long cardNumber, BigDecimal value) {
        Optional<Card> cardOptional = repository.findByCardNumber(cardNumber);
        if(cardOptional.isPresent()){
            Card card = cardOptional.get();
            card.setSubtractBalance(value);
            repository.save(card);
        }
    }

    public Boolean verifyIsCard(CardBuyRequest card){
        return repository
                .findByCardNumber(card.getCardNumber())
                .isPresent();
    }

    public Boolean verifyCardIsType(CardBuyRequest cardBuyRequest) {
        Card card = repository.findByCardNumber(cardBuyRequest.getCardNumber()).get();
        return card.getTypeCard().getTypeCard().equals(cardBuyRequest.getTypeCard());
    }

    public Boolean verifyIsBalance(CardBuyRequest card, BigDecimal value){
        Optional<Card> cardOptional =
                repository.findByCardNumber(card.getCardNumber());
        if(cardOptional.isPresent()) {
            return cardOptional.get().isBalance(value);
        }
        return false;
    }
}
