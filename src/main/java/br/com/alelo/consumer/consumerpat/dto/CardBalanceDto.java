package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CardBalanceDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cardNumber;
	private double value;
}
