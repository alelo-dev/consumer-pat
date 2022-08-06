package br.com.alelo.consumer.consumerpat.service.impl;

import br.com.alelo.consumer.consumerpat.domain.Card;
import br.com.alelo.consumer.consumerpat.domain.Consumer;
import br.com.alelo.consumer.consumerpat.exception.BusinessException;
import br.com.alelo.consumer.consumerpat.mapper.CardMapper;
import br.com.alelo.consumer.consumerpat.request.CardRequest;
import br.com.alelo.consumer.consumerpat.response.CardResponse;
import br.com.alelo.consumer.consumerpat.respository.CardRepository;
import br.com.alelo.consumer.consumerpat.respository.ConsumerRepository;
import br.com.alelo.consumer.consumerpat.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public CardResponse createCardForConsumer(CardRequest cardRequest) {
        log.info("creating card for consumer with data = {}", cardRequest);

        Consumer consumer = consumerRepository.findById(cardRequest.getConsumerId())
                .orElseThrow(() -> new BusinessException("Consumer not found", HttpStatus.UNPROCESSABLE_ENTITY));

        validationConsumerCard(consumer, cardRequest);

        Card card = cardMapper.toEntity(cardRequest);

        generatorNumberCard(card);

        card.setConsumer(consumer);

        Card savedCardForConsumer = cardRepository.save(card);

        return cardMapper.toResponse(savedCardForConsumer);
    }

    @Override
    public CardResponse addBalanceForCard(String cardNumber, BigDecimal cardBalance) {
        log.info("Add Balance For Card with card number = {}; cardBalance = {}", cardNumber, cardBalance);

        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new BusinessException("Card not found", HttpStatus.NOT_FOUND));

        validateActiveCard(card);

        card.setCardBalance(card.getCardBalance().add(cardBalance));

        Card savedBalanceForCard = cardRepository.save(card);

        return cardMapper.toResponse(savedBalanceForCard);
    }

    @Override
    public CardResponse findCardNumber(String cardNumber) {
        log.info("Find card by number = {}", cardNumber);

        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new BusinessException("Card not found", HttpStatus.NOT_FOUND));

        return cardMapper.toResponse(card);
    }

    @Override
    public CardResponse activeCard(String cardNumber) {
        log.info("Active card by number = {}", cardNumber);

        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new BusinessException("Card not found", HttpStatus.NOT_FOUND));

        validateCard(card);

        return cardMapper.toResponse(card);
    }

    private void validateActiveCard(Card card) {
        if (!card.isActive()) {
            throw new BusinessException("The card informed is not active", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void generatorNumberCard(Card card) {
        String numbers = "123456789";

        Random random = new Random();

        String storesNumbers = "";

        int index = -1;
        for (int i = 0; i < 16; i++) {
            index = random.nextInt(numbers.length());
            storesNumbers += numbers.substring(index, index + 1);
            card.setCardNumber(storesNumbers);
        }
    }

    private void validationConsumerCard(Consumer consumer, CardRequest cardRequest) {

        if (!consumer.getCards().isEmpty()) {
            for (Card card : consumer.getCards()) {
                if (cardRequest.getCardtype().equals(card.getCardtype()) && card.isActive()) {
                    throw new BusinessException("already contains a " + card.getCardtype().toString() + " card linked to the consumer", HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
        }
    }

    private void validateCard(Card card) {
        if (!card.isActive()) {
            card.setActive(true);
        } else {
            card.setActive(false);
        }
        cardRepository.save(card);
    }
}
