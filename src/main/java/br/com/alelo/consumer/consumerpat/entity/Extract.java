package br.com.alelo.consumer.consumerpat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/* Review: O uso do @Data traz algumas implementações extras que apesar de não serem utilizadas quando passarem
* em ferramentas de qualidade de código, como sonar, o mesmo entenderá que é código novo e deve ser testado */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "establishment_name_id")
    private int establishmentNameId;

    @Column(name = "establishment_name")
    private String establishmentName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "date_buy")
    private Date dateBuy;

    @Column(name = "card_number")
    private int cardNumber;

    @Column(name = "amount")
    private BigDecimal amount;

    public Extract( String productDescription, Date dateBuy, int cardNumber, BigDecimal amount) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, int cardNumber, BigDecimal amount) {
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

}
