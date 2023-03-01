package br.com.alelo.consumer.consumerpat.common.application.port.in;

import br.com.alelo.consumer.consumerpat.common.domain.Card;
import br.com.alelo.consumer.consumerpat.common.domain.CardType;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    public Card toEntity(NewCard newCard) {

        var cardType = new CardType();
        cardType.setCardTypeId(newCard.getType());

        var card = new Card();

        card.setNumber(newCard.getNumber());
        card.setType(cardType);

        return card;
    }
}
