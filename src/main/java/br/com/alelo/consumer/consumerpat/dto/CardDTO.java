package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = {"cardType", "cardNumber"})
public class CardDTO {

    private CardType cardType;

    private int cardNumber;

}
