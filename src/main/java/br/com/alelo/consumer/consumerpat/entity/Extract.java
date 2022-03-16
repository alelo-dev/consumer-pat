package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer establishmentNameId;
    @NonNull
    private String establishmentName;
    private String productDescription;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateBuy;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
    private BigDecimal value;


    public Extract(int id, Integer establishmentNameId, String establishmentName, String productDescription, Date dateBuy, Card card, BigDecimal value) {
        this.id = id;
        this.establishmentNameId = establishmentNameId;
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.card = card;
        this.value = value;
    }

    public Extract(String establishmentName, String productDescription, Date dateBuy, Card card, BigDecimal value) {
        this.establishmentName = establishmentName;
        this.productDescription = productDescription;
        this.dateBuy = dateBuy;
        this.card = card;
        this.value = value;
    }
}
