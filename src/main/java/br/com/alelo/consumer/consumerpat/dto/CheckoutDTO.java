package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import br.com.alelo.consumer.consumerpat.enums.EstablishmentType;
import lombok.Value;

@Value
public class CheckoutDTO {

    String establishmentName;

    EstablishmentType establishmentType;

    String productDescription;

    String cardNumber;

    BigDecimal value;

}
