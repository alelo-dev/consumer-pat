package br.com.alelo.consumer.consumerpat.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name= "establishmentNameId")
    private int establishmentNameId;

    @Column(name = "establishmentName")
    private String establishmentName;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name= "dateBuy")
    private Date dateBuy;

    @Column(name = "cardNumber")
    private int cardNumber;

    @Column(name = "amount")
    private Double amount;


    public Extract( String productDescription, Date dateBuy, int cardNumber, Double amount) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, Double amount) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

}
