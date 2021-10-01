package br.com.alelo.consumer.consumerpat.vo;

import lombok.Data;

@Data
public class BuyVO {
	private int establishmentType; 
	private String establishmentName; 
	private int cardNumber; 
	private String productDescription; 
	private double value;
	
	
}
