package br.com.alelo.consumer.consumerpat.util;

public class Buy {

	int establishmentType; 
	String establishmentName;
	int cardNumber; 
	String productDescription; 
	double value;
	
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
