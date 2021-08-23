package br.com.alelo.consumer.consumerpat.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ConsumerSetBalanceDto {
	
	private Integer cardNumber;
	private BigDecimal value;
}
