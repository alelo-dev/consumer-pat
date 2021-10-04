package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name = "EXTRACT")
public class Extract {

    @Id
    @Column(name = "ID_EXTRACT")
    private Long id;

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

}
