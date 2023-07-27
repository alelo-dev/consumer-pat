package br.com.alelo.consumer.consumerpat.domain.card.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.repository.CardRepository;
import br.com.alelo.consumer.consumerpat.domain.common.DomainException;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.consumer.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.lang.String.format;

public class DomainCardService implements CardService {

    private final CardRepository cardRepository;
    private final ConsumerService consumerService;
    private final LedgerService ledgerService;

    public DomainCardService(CardRepository cardRepository,
                             ConsumerService consumerService,
                             LedgerService ledgerService) {
        this.cardRepository = cardRepository;
        this.consumerService = consumerService;
        this.ledgerService = ledgerService;
    }

    public void addCard(final UUID consumerId, final Card newCard) {
        var consumer = consumerService.searchConsumerById(consumerId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Consumer [%s] not found", consumerId)));

        newCard.addConsumer(consumer);
        newCard.addCardBalance(new CardBalance(UUID.randomUUID(), newCard));

        cardRepository.saveCard(newCard);
    }

    public void updateCardBalance(final CardBalance cardBalance) {
        cardRepository.saveCardBalance(cardBalance);
    }

    public Optional<CardBalance> searchCardBalanceByCardNumber(final CardNumber cardNumber) {
        return cardRepository.findCardBalanceByCardNumber(cardNumber);
    }

    public void chargeCard(final CardNumber cardNumber, final BigDecimal amount) {
        var cardBalance = searchCardBalanceByCardNumber(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException(format("Card balance [%s] not found", cardNumber)));

        cardBalance.chargeCardBalance(amount);

        updateCardBalance(cardBalance);
        ledgerService.credit(cardBalance);
    }

    public Optional<Card> searchCardByCardNumber(final CardNumber cardNumber) {
        return cardRepository.findCardByCardNumber(cardNumber);
    }

    public Optional<Set<Card>> searchCardByConsumerId(final UUID consumerId) {
        return cardRepository.findCardByConsumerId(consumerId);
    }

}
