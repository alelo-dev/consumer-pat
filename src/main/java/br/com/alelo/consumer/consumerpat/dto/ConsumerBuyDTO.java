package br.com.alelo.consumer.consumerpat.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ConsumerBuyDTO {
	
	public ConsumerBuyDTO(int establishmentType, String establishmentName, int cardNumber, String productDescription, double value) {
		super();
		this.establishmentType = establishmentType;
		this.establishmentName = establishmentName;
		this.cardNumber = cardNumber;
		this.productDescription = productDescription;
		this.value = value;
	}

	@ApiModelProperty(notes = "Establishment Type: 1 - Food, 2 - DrugStore, 3 - Fuel.")
	private int establishmentType;
	
	@ApiModelProperty(notes = "Establishment Name.")
	private String establishmentName;
	
	@ApiModelProperty(notes = "Card Number.")
	private int cardNumber;
	
	@ApiModelProperty(notes = "Product Description.")
	private String productDescription;
	
	@ApiModelProperty(notes = "Product Value.")
	private double value;
}
