package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "extract")
public class Extract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EXTRACT_ID")
    private int extractId;

    @Column(name = "ESTABLISHMENT_ID")
    private int establishmentId;

    @Column(name = "ESTABLISHMENT_NAME")
    private String establishmentName;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;

    @Column(name = "DATE_OF_PURCHASE")
    private Date dateOfPurchase;

    @Column(name = "CARD_NUMBER")
    private int cardNumber;

    @Column(name = "AMOUNT")
    private double amount;

    public Extract(String establishmentName, String productDescription, Date dateOfPurchase, int cardNumber, double amount) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateOfPurchase = dateOfPurchase;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }
}