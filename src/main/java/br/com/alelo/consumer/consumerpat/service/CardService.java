package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Purchase;
import br.com.alelo.consumer.consumerpat.exception.CardNotAcceptedException;
import br.com.alelo.consumer.consumerpat.exception.InsufficientFundsException;
import br.com.alelo.consumer.consumerpat.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card setBalance(String cardNumber, Double value) {
        Card card = cardRepository.findByCardNumber(cardNumber);
        if (Objects.nonNull(card)) {
            card.setCardBalance(value);
            return cardRepository.save(card);
        }
        throw new NoSuchElementException("Card number " + cardNumber + " not found!");
    }

    public Card buy(Purchase purchase) throws InsufficientFundsException, CardNotAcceptedException {
        if (Objects.isNull(cardRepository.findByCardNumber(purchase.getCardNumber()))) {
            throw new NoSuchElementException("There are no cards with this card Number");
        }
        Card card = cardRepository.findByCardNumber(purchase.getCardNumber());
        if(card.getCardBalance() < purchase.getValue()){
            throw new InsufficientFundsException("There are not enough funds for this purchase");
        }
        if (purchase.getEstablishmentType() == 1 && cardRepository.findByCardNumber(purchase.getCardNumber()).getCardType().getCode() == 1) {
            // Para compras no cartão de alimentação o cliente recebe um desconto de 10%
            Double cashback = (purchase.getValue() / 100) * 10;
            Double value = purchase.getValue() - cashback;
            card.setCardBalance(card.getCardBalance()-value);

        } else if (purchase.getEstablishmentType() == 2 && cardRepository.findByCardNumber(purchase.getCardNumber()).getCardType().getCode() == 2) {
            card.setCardBalance(card.getCardBalance()-purchase.getValue());
        } else if (purchase.getEstablishmentType() == 3 && cardRepository.findByCardNumber(purchase.getCardNumber()).getCardType().getCode() == 3) {
            Double tax = (purchase.getValue() / 100) * 35;
            Double value = purchase.getValue() + tax;
            card.setCardBalance(card.getCardBalance()-value);
        } else {
            throw new CardNotAcceptedException("This establishment does not accept this type of Card.");
        }
        return card;
    }

    public Card save(@RequestBody Card card) {
        return cardRepository.save(card);
    }
}
