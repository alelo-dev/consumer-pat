package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String establishmentName;
    String productDescription;
    Date dateBuy;
    String cardNumber;
    double value;

    public Extract(String establishmentName, String productDescription, Date dateBuy, String cardNumber, double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
