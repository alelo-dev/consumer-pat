package br.com.alelo.consumer.consumerpat.infrastructure.repository.card;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardBalance;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.domain.card.repository.DomainCardRepository;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.converter.CardBalanceConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.converter.CardConverter;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CardRepository implements DomainCardRepository {

    public final CardJpaRepository cardRepository;
    public final CardBalanceJpaRepository cardBalanceRepository;
    public final CardConverter cardConverter;
    public final CardBalanceConverter cardBalanceConverter;

    @Override
    public void saveCard(Card card) {
        var cardEntity = cardConverter.toEntity(card);
        cardEntity.setCreatedAt(LocalDateTime.now());
        cardEntity.getCardBalance().setCreatedAt(LocalDateTime.now());
        cardRepository.save(cardEntity);
    }

    @Override
    public void saveCardBalance(CardBalance cardBalance) {
        var cardBalanceEntity = cardBalanceConverter.toEntity(cardBalance);
        cardBalanceEntity.setUpdatedAt(LocalDateTime.now());
        cardBalanceRepository.save(cardBalanceEntity);
    }

    @Override
    public Optional<CardBalance> findCardBalanceByCardNumber(CardNumber cardNumber) {
        var consumerFound = cardBalanceRepository.findByCardNumber(cardNumber.getCardNumber());
        return consumerFound.map(cardBalanceConverter::toDomain);
    }

    @Override
    public Optional<Card> findCardByCardNumber(CardNumber cardNumber) {
        var consumerFound = cardRepository.findByCardNumber(cardNumber.getCardNumber());
        return consumerFound.map(consumer -> {
            consumer.getCardBalance();
            return cardConverter.toDomain(consumer);
        });
    }

    @Override
    public Optional<Set<Card>> findCardByConsumerId(UUID consumerId) {
        Optional<Set<CardEntity>> cards = cardRepository.findAllByConsumerId(consumerId);
        return cards.map(cardConverter::toDomain);
    }
}
