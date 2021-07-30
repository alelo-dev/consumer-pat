package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncreaseCardBalanceDTO {
    private String number;
    private BigDecimal value;
}
