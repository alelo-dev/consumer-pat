package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="extract")
public class Extract extends BaseEntity{

    @Column
    int establishmentNameId;

    @Column
    String establishmentName;

    @Column
    String productDescription;

    @Column
    Date dateBuy;

    @Column
    int cardNumber;

    @Column
    double amount;

    @Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
		return id;
	}
    
    
    
    public int getEstablishmentNameId() {
		return establishmentNameId;
	}



	public void setEstablishmentNameId(int establishmentNameId) {
		this.establishmentNameId = establishmentNameId;
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



	public Date getDateBuy() {
		return dateBuy;
	}



	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}



	public int getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double amount) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public Extract( String productDescription, Date dateBuy, int cardNumber, double amount) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double amount) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

}
