package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Extract {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "establishmentName", nullable = true, length = 255)
    private String establishmentName;
	
	@Column(name = "productDescription", nullable = false, length = 255)
    private String productDescription;
	
	@Column(name = "dateBuy", nullable = false)
    private Date dateBuy;
	
	@Column(name = "cardNumber", nullable = false, length = 50)
    private String cardNumber;
	
	@Column(name = "value", nullable = false)
    private double value;

	public Extract() {
		
	}

    public Extract(int id, String establishmentName, String productDescription, Date dateBuy, String cardNumber, double value) {
        this.id = id;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract( String productDescription, Date dateBuy, String cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, String cardNumber, double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
