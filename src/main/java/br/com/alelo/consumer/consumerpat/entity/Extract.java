package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 *  - Retirado establishmentNameId do construtor pois nao esta sendo utilizado.
 * @author HLJunior
 *
 */

@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int establishmentNameId;
    String establishmentName;
    String productDescription;
    Date dateBuy;
    int cardNumber;
    double value;


    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract( String productDescription, Date dateBuy, int cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
