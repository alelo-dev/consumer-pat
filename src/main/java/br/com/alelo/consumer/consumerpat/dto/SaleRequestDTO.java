package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleRequestDTO implements Serializable{

	private Long establishmentId;
	
	private String cardNumber;
	
	private String productDescription;
	
	private double value;
}
