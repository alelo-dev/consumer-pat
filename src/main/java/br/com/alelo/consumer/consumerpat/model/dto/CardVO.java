package br.com.alelo.consumer.consumerpat.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardVO {

	private Integer foodCardNumber;
	private Integer fuelCardNumber;
	private Integer drugstoreNumber;
	private Double fuelCardBalance;
	private Double foodCardBalance;
	private Double drugstoreCardBalance;
}
