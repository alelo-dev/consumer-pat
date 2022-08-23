package br.com.alelo.consumer.consumerpat.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import java.util.Date;


@Data
@Entity
public class Lancamento {

    @Id
    private Long id;
        
    private String productDescription;
    
    private Date dateBuy;
        
    private double value;
    
    @ManyToOne
    private Card card;
    
    @ManyToOne
    private Establishment establishment;
}
