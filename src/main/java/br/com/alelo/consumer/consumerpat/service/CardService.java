package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.controller.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.orm.CardORM;
import br.com.alelo.consumer.consumerpat.entity.transaction.Recharge;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository allCards;

    public CardService(CardRepository allCards) {
        this.allCards = allCards;
    }

    public CardORM persist(CardDTO newCard) {
        if (newCard == null) {
            return null;
        }
        var card = new CardORM();
        card.setType(newCard.getType());
        card.setBalance(newCard.getBalance());
        card.setNumber(newCard.getNumber());
        return allCards.save(card);
    }

    public CardORM merge(CardORM current, CardDTO latest) {
        if (current == null) {
            return persist(latest);
        }
        current.setType(latest.getType());
        current.setNumber(latest.getNumber());
        return allCards.save(current);
    }

    public UpdateActionResponse recharge(String cardNumber, double value) {
        var found = allCards.findById(cardNumber);
        if (found.isEmpty()) {
            return UpdateActionResponse.NOT_FOUND;
        }

        var card = found.get();
        card.add(new Recharge(value));

        allCards.save(card);
        return UpdateActionResponse.UPDATED;
    }

}
