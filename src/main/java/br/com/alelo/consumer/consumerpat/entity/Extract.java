package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Data
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long establishmentNameId;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;


    public Extract( String productDescription, Date dateBuy, Card card, BigDecimal value) {
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.card = card;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, Card card, BigDecimal value) {
        this(productDescription, dateBuy, card, value);
        this.establishmentName = establishmentName;   
    }
}
