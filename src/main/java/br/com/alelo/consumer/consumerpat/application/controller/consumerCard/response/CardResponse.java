package br.com.alelo.consumer.consumerpat.application.controller.consumerCard.response;

import br.com.alelo.consumer.consumerpat.domain.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    Set<Card> cards;

    public static CardResponse of(Set<Card> cards) {
        return new CardResponse(cards);
    }
}
