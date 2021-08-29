package br.com.alelo.consumer.consumerpat.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private Date buyDate;
    private String cardNumber;
    private BigDecimal value;


    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date buyDate, String cardNumber, BigDecimal value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.buyDate = buyDate;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract( String productDescription, Date buyDate, String cardNumber, BigDecimal value) {
        this.productDescription = productDescription;
        this.buyDate = buyDate;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date buyDate, String cardNumber, BigDecimal value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.buyDate = buyDate;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
