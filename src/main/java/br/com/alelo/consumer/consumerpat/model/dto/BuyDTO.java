package br.com.alelo.consumer.consumerpat.model.dto;

import lombok.Data;

@Data
public class BuyDTO {
	
	private int establishmentType;
	private String establishmentName;
	private Long cardNumber;
	private String productDescription;
	private Double value;

}
