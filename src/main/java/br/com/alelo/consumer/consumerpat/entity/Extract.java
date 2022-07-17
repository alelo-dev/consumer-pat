package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;


@Data
@Entity
public class Extract {

    @Id
    int id;
    /*
    int establishmentNameId;
    String establishmentName;

     */

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    Establishment establishment;

    String productDescription;
    Date dateBuy;
    int cardNumber;
    double value;


    public Extract(int id, Establishment establishment, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.id = id;
        this.establishment = establishment;
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

    public Extract(Establishment establishment, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.establishment = establishment;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
