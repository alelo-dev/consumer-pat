package br.com.alelo.consumer.consumerpat.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    private String productDescription;
    
    private Date dateBuy;
        
    private double value;
    
    @ManyToOne
    private Card card;
    
    @ManyToOne
    private Establishment establishment;
}
