package br.com.alelo.consumer.consumerpat.dto;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.Value;

@Value
public class UpdateCardDTO {

    String cardNumber;

    CardType cardType;

}
