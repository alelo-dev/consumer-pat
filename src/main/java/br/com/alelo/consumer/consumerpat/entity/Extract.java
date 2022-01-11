package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

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
    private int id;

    private int establishmentNameId;

    private String establishmentName;

    private String productDescription;

    private Date dateBuy;

    private Long cardNumber;

    private BigDecimal value;

}
