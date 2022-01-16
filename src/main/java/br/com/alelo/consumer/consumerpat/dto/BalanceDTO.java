package br.com.alelo.consumer.consumerpat.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class BalanceDTO implements Serializable {

    private BigDecimal value;
    private String cardNumber;
    private String cardType;

    public boolean validateMe() {

        return (value != null && cardNumber != null && cardType != null);
    }
}
