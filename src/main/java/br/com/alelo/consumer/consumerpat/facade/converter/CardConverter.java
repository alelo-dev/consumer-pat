package br.com.alelo.consumer.consumerpat.facade.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import br.com.alelo.consumer.consumerpat.controller.dto.CardDto;
import br.com.alelo.consumer.consumerpat.controller.dto.CardSaveDto;
import br.com.alelo.consumer.consumerpat.model.Card;
import br.com.alelo.consumer.consumerpat.model.Consumer;
import br.com.alelo.consumer.consumerpat.model.type.CardType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class CardConverter {

    public static Collection<CardDto> toDtoList(final Collection<Card> cards) {

        if (cards == null) {
            return new ArrayList<>();
        }

        return cards.stream().map(card -> {
            return CardDto.builder().balance(card.getBalance()).consumerId(card.getConsumer().getId()).id(card.getId())
                    .number(card.getNumber()).type(card.getType().name()).build();
        }).collect(Collectors.toList());
    }

    public static Collection<CardDto> toDtoSaveList(final Collection<CardSaveDto> cards) {

        if (cards == null) {
            return null;
        }

        return cards.stream().map(card -> {
            return CardDto.builder().balance(card.getBalance()).number(card.getNumber()).type(card.getType()).build();
        }).collect(Collectors.toList());
    }

    public static Card toEntity(final CardDto card) {
        return Card.builder().id(card.getId()).consumer(Consumer.builder().id(card.getConsumerId()).build())
                .balance(card.getBalance()).number(card.getNumber()).type(CardType.valueOf(card.getType())).build();
    }

    public static Collection<Card> toList(final Collection<CardDto> cards) {

        if (cards == null) {
            return null;
        }

        return cards.stream().map(card -> {
            return Card.builder().id(card.getId()).number(card.getNumber()).balance(card.getBalance())
                    .type(CardType.valueOf(card.getType()))
                    .consumer(Consumer.builder().id(card.getConsumerId()).build()).build();
        }).collect(Collectors.toList());
    }

}
