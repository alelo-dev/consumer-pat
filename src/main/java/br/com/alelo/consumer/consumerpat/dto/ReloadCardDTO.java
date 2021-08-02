package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class ReloadCardDTO {

    String cardNumber;

    BigDecimal value;

}
