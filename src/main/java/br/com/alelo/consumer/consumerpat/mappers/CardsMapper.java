package br.com.alelo.consumer.consumerpat.mappers;

import br.com.alelo.consumer.consumerpat.domain.model.Cards;
import br.com.alelo.consumer.consumerpat.dto.CardsCreationDto;
import br.com.alelo.consumer.consumerpat.dto.CardsResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class CardsMapper {

    public static List<CardsResponseDto> mapCardsToCardsResponseDto(List<Cards> cards){
        return cards.stream().map(card ->CardsResponseDto.builder()
                .cardBalance(card.getCardBalance())
                .id(card.getId())
                .cardType(card.getCardType())
                .cardNumber(card.getCardNumber())
                .build()).collect(Collectors.toList());
    }

    public static List<Cards> mapCardsCreationDtoToCards(List<CardsCreationDto> cardsCreationDto){
        return cardsCreationDto.stream().map(card-> {
            Cards cards = new Cards();
            cards.setCardNumber(card.getCardNumber());
            cards.setCardType(card.getCardType());
            cards.setCardBalance(card.getCardBalance());
            return cards;
        }).collect(Collectors.toList());

    }
}
