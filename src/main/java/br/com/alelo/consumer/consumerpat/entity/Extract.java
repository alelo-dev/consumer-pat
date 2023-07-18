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
    @Column(name = "extract_id")
    private int extractId;

    @Column(name = "establishment_id")
    private int establishmentId;

    @Column(name = "establishment_name")
    private String establishmentName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "date_of_purchase")
    private Date dateOfPurchase;

    @Column(name = "card_number")
    private int cardNumber;

    @Column(name = "amount")
    private double amount;

    public Extract(String establishmentName, String productDescription, Date dateOfPurchase, int cardNumber, double amount) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateOfPurchase = dateOfPurchase;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }
}