package br.com.alelo.consumer.consumerpat.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentBalanceValue {

    private BigDecimal balanceValue;

    public CurrentBalanceValue(BigDecimal value) {
        this.balanceValue = value;
    }
}
