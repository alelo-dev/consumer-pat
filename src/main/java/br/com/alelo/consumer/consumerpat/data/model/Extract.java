package br.com.alelo.consumer.consumerpat.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor
@Data
@Entity
public class Extract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "ESTABLISHMENT_NAME")
    String establishmentName;

    @Column(name = "PRODUCT_DESCRIPTION")
    String productDescription;

    @Column(name = "DATE_BUY")
    Date dateBuy;

    @Column(name = "ID_CARD")
    Integer cardId;

    @Column(name = "AMOUNT")
    BigDecimal amount;

    public Extract(String establishmentName, String productDescription, Date date, Integer cardId, BigDecimal amount) {
        this.establishmentName = establishmentName;
        this.amount = amount;
        this.dateBuy = date;
        this.productDescription = productDescription;
        this.cardId = cardId;
    }
}
