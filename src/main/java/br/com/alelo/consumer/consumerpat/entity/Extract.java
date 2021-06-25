package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Extract {

    @Id
    int id;
    int establishmentNameId;
    String establishmentName;
    String productDescription;
    Date dateBuy;
    Long cardNumber;
    double value;

    public Extract( String productDescription, Date dateBuy, Long cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, Long cardNumber, double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
