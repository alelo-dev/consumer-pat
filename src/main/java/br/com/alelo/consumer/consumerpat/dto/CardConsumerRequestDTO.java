package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

@Data
public class CardConsumerRequestDTO {


	private Long typeCard;

	private String cardNumber;
	
	private Double balance;
	
}
