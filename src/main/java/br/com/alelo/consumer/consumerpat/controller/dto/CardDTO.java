package br.com.alelo.consumer.consumerpat.controller.dto;

import br.com.alelo.consumer.consumerpat.entity.Card;
import br.com.alelo.consumer.consumerpat.entity.CardType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardDTO {

    private String number;
    private double balance;
    private CardType type;

    public static CardDTO from(Card card) {
        var dto = new CardDTO();
        dto.number = card.getNumber();
        dto.balance = card.getBalance();
        dto.type = card.getType();
        return dto;
    }

}
