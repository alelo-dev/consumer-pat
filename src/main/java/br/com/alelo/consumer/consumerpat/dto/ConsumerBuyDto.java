package br.com.alelo.consumer.consumerpat.dto;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class ConsumerBuyDto {
	private Integer establishmentType;
	private String establishmentName;
	private Integer cardNumber;
	private String productDescription;
	private BigDecimal value;
}
