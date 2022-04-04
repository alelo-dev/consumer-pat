package br.com.alelo.consumer.consumerpat.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@ApiModel (description = "Represents the data needed to create a card")
public class CardDto {

	@ApiModelProperty(required = true, value = "Card type. It Must be: 'FOOD', 'FUEL'or 'DRUGSTORE'")
	@NotEmpty(message = "The field 'type' is mandatory. ")
	@Pattern(regexp = "FOOD|FUEL|DRUGSTORE", flags = Pattern.Flag.CASE_INSENSITIVE, message = "The field 'type' is invalid. It Must be: 'FOOD', 'FUEL'or 'DRUGSTORE'")
	private String type;	
	
	@ApiModelProperty(required = true, value = "Number of card.")
	@NotNull(message = "The field 'number' is mandatory.")
	private Integer number;

	@ApiModelProperty(required = true, value = "Card value balance.")
	@NotNull(message = "The field 'balance' is mandatory.")
    private Double balance;	


	public CardDto() {

	}

	//-----------Getters and Setters-------------//
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}


	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}