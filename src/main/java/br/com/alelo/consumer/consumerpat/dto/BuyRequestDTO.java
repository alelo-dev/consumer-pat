package br.com.alelo.consumer.consumerpat.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Consumidor requisição DTO")
public class BuyRequestDTO {
	
	@ApiModelProperty(value = "Establishment Type")
    @NotBlank(message = "Establishment Type")
    @Min(value = 1, message = "Establishment Type")
    @Max(value = 3, message = "Establishment Type")
    private int establishmentType;
    
    @ApiModelProperty(value = "Establishment Name")
    @Length(min = 0, max = 255, message = "Establishment Name")
    private String establishmentName;
    
    @ApiModelProperty(value = "Card number")
    @NotBlank(message = "Card number")
    @Min(value = 0, message = "Card number")
    @Max(value = 2147483647, message = "Card number")
    private int cardNumber;
    
    @ApiModelProperty(value = "Product description")
    @Length(min = 0, max = 255, message = "Product description")
    private String productDescription;
    
    @ApiModelProperty(value = "Value")
    @NotNull(message = "Value")
    private double value;

	public int getEstablishmentType() {
		return establishmentType;
	}

	public void setEstablishmentType(int establishmentType) {
		this.establishmentType = establishmentType;
	}

	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
    
}
