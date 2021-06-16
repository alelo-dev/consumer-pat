package br.com.alelo.consumer.consumerpat.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@Data
@Entity
public class Extract {

    @Id
    private int id;
    private int establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private LocalDate dateBuy;
    private int cardNumber;
    private double value;


    public Extract( String productDescription, LocalDate dateBuy, int cardNumber, double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, LocalDate dateBuy, int cardNumber, double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
