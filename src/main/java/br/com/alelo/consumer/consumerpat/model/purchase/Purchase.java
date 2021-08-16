package br.com.alelo.consumer.consumerpat.model.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

	private Integer establishmentType; 
	private String establishmentName; 
	private String cardNumber;	
	private String productDescription; 
	private Double value;
}
