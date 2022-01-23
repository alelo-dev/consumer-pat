package br.com.alelo.consumer.consumerpat.request;

import lombok.Data;

@Data
public class UpdateBalanceRequest {
	
	private Integer cardNumber;
	private Double value;

}
