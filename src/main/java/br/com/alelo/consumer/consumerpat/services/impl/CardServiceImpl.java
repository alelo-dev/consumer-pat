package br.com.alelo.consumer.consumerpat.services.impl;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.Consumer;
import br.com.alelo.consumer.consumerpat.exception.CardNotFoundException;
import br.com.alelo.consumer.consumerpat.exception.CardNumberNotFoundException;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.services.CardService;
import br.com.alelo.consumer.consumerpat.services.ConsumerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ConsumerService consumerService;

    public CardServiceImpl(CardRepository cardRepository, ConsumerService consumerService) {
        this.cardRepository = cardRepository;
        this.consumerService = consumerService;
    }

    @Override
    public Card findOrFail(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id) {
                });
    }

    public Card findByCardNumber(Integer number) {
        return cardRepository.findCardByNumber(number)
                .orElseThrow(() -> new CardNumberNotFoundException(number) {
                });
    }

    @Override
    public Card save(Card card) {

        Consumer consumer = consumerService.findOrFail(card.getConsumer().getId());
        card.setConsumer(consumer);

        return cardRepository.save(card);
    }

    @Transactional
    @Override
    public Card increaseCardBalance(Integer cardNumber, BigDecimal amount) {

        Card currentCard = this.findByCardNumber(cardNumber);
        currentCard.increaseBalance(amount);

        return currentCard;

    }

    @Transactional
    @Override
    public Card decreaseBalance(Integer cardNumber, BigDecimal amount) {

        Card currentCard = this.findByCardNumber(cardNumber);
        currentCard.decreaseBalance(amount);

        return currentCard;
    }

}
