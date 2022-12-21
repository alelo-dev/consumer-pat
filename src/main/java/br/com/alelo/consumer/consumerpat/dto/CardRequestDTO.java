package br.com.alelo.consumer.consumerpat.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Consumidor requisição DTO")
public class CardRequestDTO {
	
	@ApiModelProperty(value = "Card number")
    @NotBlank(message = "Card number")
    @Min(value = 0, message = "Card number")
    @Max(value = 2147483647, message = "Card number")
    private int cardNumber;
    
    @ApiModelProperty(value = "Card balance value to add")
    @NotBlank(message = "Card balance value to add")
    @Min(value = 0, message = "Card balance value to add")
    private double CardBalanceValue;
    
    @ApiModelProperty(value = "Type Card")
    @NotBlank(message = "Type number")
    @Min(value = 1, message = "Type number")
    @Max(value = 3, message = "Type number")
    private int typeCard;

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getCardBalanceValue() {
		return CardBalanceValue;
	}

	public void setCardBalanceValue(double cardBalanceValue) {
		CardBalanceValue = cardBalanceValue;
	}

	public int getTypeCard() {
		return typeCard;
	}

	public void setTypeCard(int typeCard) {
		this.typeCard = typeCard;
	}    
    
}
