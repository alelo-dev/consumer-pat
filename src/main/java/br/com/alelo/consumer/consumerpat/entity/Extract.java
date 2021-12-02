package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity(name = "EXTRACT")
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ESTABLISHMENT_NAME_ID")
    private int establishmentNameId;

    @Column(name = "ESTABLISHMENT_NAME")
    private String establishmentName;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "DATE_BUY")
    private Date dateBuy;

    @Column(name = "CARD_NUMBER")
    private int cardNumber;

    @Column(name = "VALUE")
    private double value;

    public Extract(final String productDescription, final Date dateBuy, final int cardNumber, final double value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public Extract(final String establishmentName, final String productDescription, final Date dateBuy,
                   final int cardNumber, final double value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.value = value;
    }
}
