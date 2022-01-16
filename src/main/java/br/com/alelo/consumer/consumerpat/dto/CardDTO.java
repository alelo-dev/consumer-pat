package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class CardDTO implements Serializable {

    private String number;
    private BigDecimal balance;
    private CardType cardType;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.number = card.getNumber();
        this.balance = card.getBalance();
        this.cardType = card.getCardType();
    }
}
