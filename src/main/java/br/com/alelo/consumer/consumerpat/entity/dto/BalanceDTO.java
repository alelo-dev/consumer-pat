package br.com.alelo.consumer.consumerpat.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceDTO implements Serializable {

    private BigDecimal value;
    private String cardNumber;
    private String cardType;

    public boolean validateMe() {

        return (value != null && cardNumber != null && cardType != null);
    }
}