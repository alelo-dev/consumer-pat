package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BalanceDto {
    
    private Long cardNumber; 
    private BigDecimal value;
}