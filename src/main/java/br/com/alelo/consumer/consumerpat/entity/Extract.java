package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private int cardNumber;
    private double value;

    //Getters and setters
    public int getId() {
		return id;
	}

	public int getEstablishmentNameId() {
		return establishmentNameId;
	}

	public String getEstablishmentName() {
		return establishmentName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public Date getDateBuy() {
		return dateBuy;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public double getValue() {
		return value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEstablishmentNameId(int establishmentNameId) {
		this.establishmentNameId = establishmentNameId;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setValue(double value) {
		this.value = value;
	}

    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.setId(id);
        this.setEstablishmentNameId(establishmentNameId);
        this.setEstablishmentName(establishmentName);
        this.setProductDescription(productDescription);
        this.setDateBuy(dateBuy);
        this.setCardNumber(cardNumber);
        this.setValue(value);
    }

    public Extract( String productDescription, Date dateBuy, int cardNumber, double value) {
        this.setProductDescription(productDescription);
        this.setDateBuy(dateBuy);
        this.setCardNumber(cardNumber);
        this.setValue(value);
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.setEstablishmentName(establishmentName);
        this.setProductDescription(productDescription);
        this.setDateBuy(dateBuy);
        this.setCardNumber(cardNumber);
        this.setValue(value);
    }
}
