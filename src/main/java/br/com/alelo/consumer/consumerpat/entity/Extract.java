package br.com.alelo.consumer.consumerpat.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
@Entity
@Builder
public class Extract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private CardType cardType;
    private String productDescription;
    private Date dateBuy;
    private Integer cardNumber;
    private Double value;
    
}
