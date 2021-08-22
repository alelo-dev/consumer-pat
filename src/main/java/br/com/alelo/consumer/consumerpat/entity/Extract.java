package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@Getter
@Setter
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int establishmentId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private int cardNumber;
    private double value;


    public Extract(int id, int establishmentId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.id = id;
        this.establishmentId = establishmentId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String productDescription, Date dateBuy, int cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(int establishmentId, String establishmentName, String productDescription, Date dateBuy, int cardNumber, double value) {
        this.establishmentId = establishmentId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
