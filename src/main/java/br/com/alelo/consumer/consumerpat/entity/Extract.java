package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @EqualsAndHashCode.Exclude
    int id;

    int establishmentNameId;
    String establishmentName;
    String productDescription;
    @EqualsAndHashCode.Exclude Date dateBuy;
    int cardNumber;
    double value;

    public Extract(
            String establishmentName,
            String productDescription,
            Date dateBuy,
            int cardNumber,
            double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract() {}
}
