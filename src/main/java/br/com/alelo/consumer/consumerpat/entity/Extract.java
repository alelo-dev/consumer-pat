package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Extract {

    @Id
    int id;
    private int establishmentNameId;
    private String establishmentName;
    private Long cardNumber;
    private String productDescription;
    private Date dateBuy;
    private double value;


    public Extract(int id, int establishmentNameId, String establishmentName, String productDescription, Date dateBuy, Long cardNumber, double value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract( String productDescription, Date dateBuy, Long cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, Long cardNumber, double value) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
