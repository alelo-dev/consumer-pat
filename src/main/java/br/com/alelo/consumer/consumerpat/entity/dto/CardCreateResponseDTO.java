package br.com.alelo.consumer.consumerpat.entity.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;

import java.io.Serializable;
import java.math.BigDecimal;

public class CardCreateResponseDTO implements Serializable {

    private Integer number;
    private CardType cardType;

    public CardCreateResponseDTO() {
    }

    public CardCreateResponseDTO(Card card) {
        this.number = card.getCardNumber();
        this.cardType = card.getCardType();
    }
}
