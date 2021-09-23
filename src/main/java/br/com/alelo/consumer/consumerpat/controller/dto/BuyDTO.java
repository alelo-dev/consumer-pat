package br.com.alelo.consumer.consumerpat.controller.dto;

public class BuyDTO {

	int establishmentType;
	String establishmentName;
	String productDescription;
	double value;

	public BuyDTO() {
		super();
	}

	public BuyDTO(int establishmentType, String establishmentName, String productDescription, double value) {
		super();
		this.establishmentType = establishmentType;
		this.establishmentName = establishmentName;
		this.productDescription = productDescription;
		this.value = value;
	}

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
