package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.enums.CardType;
import lombok.Value;

@Value
public class CreateCardDTO {

    String cardNumber;

    BigDecimal balance;

    CardType cardType;

}
