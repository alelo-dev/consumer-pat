package br.com.alelo.consumer.consumerpat.request;

import br.com.alelo.consumer.consumerpat.entity.EstablishmentType;
import lombok.Data;

@Data
public class BuyRequest {
	
	private String establishmentName;
	private Integer cardNumber;
	private String productDescription;
	private Double value;
	private EstablishmentType typeEstablishment;

}
