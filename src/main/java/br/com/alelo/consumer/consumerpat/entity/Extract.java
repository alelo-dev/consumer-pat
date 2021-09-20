package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Extract {

	@Id
	private int id;
	private int establishmentNameId;
	private String establishmentName;
	private String productDescription;
	private Date dateBuy;
	private int cardNumber;
	private double value;

	public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy,
			int cardNumber, double value) {
		this.id = id;
		this.establishmentNameId = establishmentNameId;
		this.establishmentName = establishmentName;
		this.productDescription = productDescription;
		this.dateBuy = dateBuy;
		this.cardNumber = cardNumber;
		this.value = value;
	}

	public Extract(String productDescription, Date dateBuy, int cardNumber, double value) {
		this.productDescription = productDescription;
		this.dateBuy = dateBuy;
		this.cardNumber = cardNumber;
		this.value = value;
	}

	public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
		// this.establishmentNameId = establishmentNameId;
		this.establishmentName = establishmentName;
		this.productDescription = productDescription;
		this.dateBuy = dateBuy;
		this.cardNumber = cardNumber;
		this.value = value;
	}
}
