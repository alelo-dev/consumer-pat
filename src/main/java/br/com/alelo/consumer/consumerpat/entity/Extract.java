package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ESTABLISHMENT_NAME")
    String establishmentName;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;

    @Column(name = "CARD_NUMBER")
    private Long cardNumber;

    @Column(name = "PURCHASE_VALUE")
    private BigDecimal purchaseValue;

    public Extract(String establishmentName, String productDescription, Date purchaseDate, Long cardNumber, BigDecimal purchaseValue) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.purchaseDate = purchaseDate;
        this.cardNumber = cardNumber;
        this.purchaseValue = purchaseValue;
    }
}
