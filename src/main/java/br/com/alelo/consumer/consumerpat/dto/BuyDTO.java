package br.com.alelo.consumer.consumerpat.dto;

import java.io.Serializable;

public class BuyDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private int establishmentType;
	private Double value;
	private Long cardNumber;
	private String establishmentName;
	private String productDescription;
	
	public int getEstablishmentType() {
		return establishmentType;
	}
	public void setEstablishmentType(int establishmentType) {
		this.establishmentType = establishmentType;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getEstablishmentName() {
		return establishmentName;
	}
	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
}
