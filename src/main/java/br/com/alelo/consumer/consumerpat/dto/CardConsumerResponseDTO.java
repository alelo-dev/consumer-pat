package br.com.alelo.consumer.consumerpat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardConsumerResponseDTO {

	
	private String typeCard;

	private String cardNumber;
	
	private Double balance;
	
}
