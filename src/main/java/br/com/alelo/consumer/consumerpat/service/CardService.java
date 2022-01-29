package br.com.alelo.consumer.consumerpat.service;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.dto.ConsumerDTO;
import br.com.alelo.consumer.consumerpat.entity.dto.TransactionDTO;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

//TODO rever ajustar
@Service
public class CardService {

    private final CardRepository repository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.repository = cardRepository;
    }

    public Card findByCardNumber(Integer cardNumber) {
        return repository.findByCardNumber(cardNumber);
    }


    public Card debtByCard(Card card, BigDecimal value) {

        card.setBalanceValue(card.getBalanceValue().subtract(value));
        card = repository.save(card);
        return card;
    }

    public TransactionDTO credit(int cardNumber, BigDecimal value) {

        Card card = repository.findByCardNumber(cardNumber);

        if (Objects.nonNull(card)) {
            creditByCard(card, value);
        }

        return TransactionDTO.
                builder().build();
    }

    public Card creditByCard(Card card, BigDecimal value) {
        card.setBalanceValue(card.getBalanceValue().add(value));
        return repository.save(card);

    }


}
