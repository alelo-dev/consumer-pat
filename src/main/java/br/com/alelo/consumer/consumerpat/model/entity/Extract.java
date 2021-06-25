package br.com.alelo.consumer.consumerpat.model.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Extract {

    @Id
    int id;
    int establishmentNameId;
    String establishmentName;
    String productDescription;
    Date dateBuy;
    BigInteger cardNumber;
    double value;


    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, BigInteger cardNumber, double value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract( String productDescription, Date dateBuy, BigInteger cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, BigInteger cardNumber, double value) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
