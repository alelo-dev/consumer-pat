package br.com.alelo.consumer.consumerpat.dto;

public class RequestBuy {
	private int establishmentType;
	private String establishmentName;
	private String productDescription;
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
