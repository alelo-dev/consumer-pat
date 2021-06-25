package br.com.alelo.consumer.consumerpat.model.dto;

import java.io.Serializable;
import java.math.BigInteger;


public class BuyDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer establishmentType;
	private BigInteger cardNumber;
	private String establishmentName;
	private Double value;
	private String productDescription;
	
	
	public BuyDTO() {
		
	}


	public Integer getEstablishmentType() {
		return establishmentType;
	}


	public void setEstablishmentType(Integer establishmentType) {
		this.establishmentType = establishmentType;
	}


	public BigInteger getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(BigInteger cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getEstablishmentName() {
		return establishmentName;
	}


	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	
	
}
