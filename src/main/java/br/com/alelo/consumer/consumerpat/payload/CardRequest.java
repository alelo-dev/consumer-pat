package br.com.alelo.consumer.consumerpat.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardRequest {
	
	private Integer establishmentType;
	private String establishmentName; 
	private String cardNumber; 
	private String productDescription;
	private Double value;
	
}