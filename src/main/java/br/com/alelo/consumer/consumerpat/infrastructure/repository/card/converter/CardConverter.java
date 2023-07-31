package br.com.alelo.consumer.consumerpat.infrastructure.repository.card.converter;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import br.com.alelo.consumer.consumerpat.domain.card.entity.CardNumber;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardBalanceEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.card.entity.CardEntity;
import br.com.alelo.consumer.consumerpat.infrastructure.repository.consumer.converter.ConsumerConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CardConverter {

    private final ConsumerConverter consumerConverter;
    private final CardBalanceConverter cardBalanceConverter;

    public CardEntity toEntity(final Card card) {

        var cardBalanceEntity = CardBalanceEntity.builder()
                .cardNumber(card.getCardNumber().getCardNumber())
                .amount(card.getCardBalance().getAmount())
                .build();

        var cardEntity = CardEntity.builder()
                .cardNumber(card.getCardNumber().getCardNumber())
                .cardType(card.getCardType())
                .consumer(card.getConsumer() != null ? consumerConverter.toEntity(card.getConsumer()) : null)
                .cardBalance(cardBalanceEntity)
                .build();

        return cardEntity;
    }

    public Card toDomain(final CardEntity cardEntity) {
        var card = new Card(new CardNumber(cardEntity.getCardNumber()), cardEntity.getCardType());
        card.addCardBalance(cardBalanceConverter.toDomain(cardEntity.getCardBalance()));
        card.addConsumer(consumerConverter.toDomain(cardEntity.getConsumer()));
        return card;
    }

    public Set<Card> toDomain(Set<CardEntity> cardEntities) {
        return cardEntities.stream().map(this::toDomain).collect(Collectors.toSet());
    }

}
