package br.com.alelo.consumer.consumerpat.entity.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.enumeration.CardType;

import java.io.Serializable;
import java.math.BigDecimal;

public class CardDTO implements Serializable {

    private int number;
    private BigDecimal balance;
    private CardType cardType;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.number = card.getCardNumber();
        this.balance = card.getBalanceValue();
        this.cardType = card.getCardType();
    }
}
