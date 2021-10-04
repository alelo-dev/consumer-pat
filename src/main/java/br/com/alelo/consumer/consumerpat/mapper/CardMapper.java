package br.com.alelo.consumer.consumerpat.mapper;

import br.com.alelo.consumer.consumerpat.dto.CardDTO;
import br.com.alelo.consumer.consumerpat.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardMapper {

    public List<CardDTO> cardToListCardDTO(final List<Card> cards){

        List<CardDTO> cardsDTO= new ArrayList<>();

        for (Card card : cards) {
            CardDTO cardDTO = CardDTO.builder()
                    .cardNumber(card.getCardNumber())
                    .cardType(card.getCardType())
                    .build();

            cardsDTO.add(cardDTO);
        }

        return cardsDTO;
    }

    public List<Card> cardDTOToListCard(final List<CardDTO> cardsTDO){

        List<Card> cards = new ArrayList<>();

        for (CardDTO cardDTO : cardsTDO) {
            Card card = Card.builder()
                    .cardNumber(cardDTO.getCardNumber())
                    .cardType(cardDTO.getCardType())
                    .build();

            cards.add(card);
        }

        return cards;
    }


}
