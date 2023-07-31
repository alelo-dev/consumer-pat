package br.com.alelo.consumer.consumerpat.domain.card.service;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.repository.DomainCardRepository;
import br.com.alelo.consumer.consumerpat.domain.common.ResourceNotFoundException;
import br.com.alelo.consumer.consumerpat.domain.consumer.service.ConsumerService;
import br.com.alelo.consumer.consumerpat.domain.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DomainCardService implements CardService {

    private final DomainCardRepository cardRepository;
    private final ConsumerService consumerService;
    private final LedgerService ledgerService;

    public void addCard(final UUID consumerId, final Card newCard) {
        var consumer = consumerService.searchConsumerById(consumerId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Consumer [%s] not found", consumerId)));
        var cardBalance = new CardBalance(newCard.getCardNumber());

        newCard.addConsumer(consumer);
        newCard.addCardBalance(cardBalance);

        cardRepository.saveCard(newCard);
    }

    public void updateCard(final Card card) {
        cardRepository.saveCard(card);
    }

    public Optional<CardBalance> searchCardBalanceByCardNumber(final CardNumber cardNumber) {
        return cardRepository.findCardBalanceByCardNumber(cardNumber);
    }

    public void chargeCard(final CardNumber cardNumber, final BigDecimal amount) {
        var card = searchCardByCardNumber(cardNumber)
                .orElseThrow(() -> new ResourceNotFoundException(format("Card [%s] not found", cardNumber)));

        card.getCardBalance().chargeCardBalance(amount);

        updateCard(card);
        ledgerService.credit(card);
    }

    public Optional<Card> searchCardByCardNumber(final CardNumber cardNumber) {
        return cardRepository.findCardByCardNumber(cardNumber);
    }

    public Optional<Set<Card>> searchCardByConsumerId(final UUID consumerId) {
        return cardRepository.findCardByConsumerId(consumerId);
    }

}
