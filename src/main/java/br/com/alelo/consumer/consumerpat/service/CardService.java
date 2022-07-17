package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card findById(int cardNumber) {

        return cardRepository.findById(cardNumber)
                .orElseThrow(() -> new CardNotFoundException(cardNumber));

    }

    public void setCardBalance(int cardNumber, double value) {

        Card card = this.findById(cardNumber);

        double newBalance = card.getCardBalance() + value;
        card.setCardBalance(newBalance);
        cardRepository.save(card);
    }

    public void setWithdrawal(int cardNumber, double value) {

        Card card = this.findById(cardNumber);

        double newBalance = card.getCardBalance() - value;
        card.setCardBalance(newBalance);
        cardRepository.save(card);
    }
}
