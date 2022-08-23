package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

@Data
public class BuyDto {

	private Long establishmentId;
	
	private String productDescription;
	
	private double value;
}
