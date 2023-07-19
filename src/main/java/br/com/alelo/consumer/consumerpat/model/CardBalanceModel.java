package br.com.alelo.consumer.consumerpat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceModel {

    BigDecimal value;

    Integer cardNumber;

}
