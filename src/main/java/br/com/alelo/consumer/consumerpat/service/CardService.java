package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.CardORM;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository allCards;

    public CardService(CardRepository allCards) {
        this.allCards = allCards;
    }

    public CardORM save(CardDTO newCard) {
        if (newCard == null) {
            return null;
        }
        var card = new CardORM();
        card.setType(newCard.getType());
        card.setBalance(newCard.getBalance());
        card.setNumber(newCard.getNumber());
        return allCards.save(card);
    }
}
