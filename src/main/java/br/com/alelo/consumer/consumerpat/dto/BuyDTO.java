package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

@Data
public class BuyDTO {
	public int establishmentType; 
	public String establishmentName;
	public int cardNumber;
	public String productDescription;
	public double value;
}
