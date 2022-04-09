package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Extract implements Serializable {

	private static final long serialVersionUID = 3465500229975213476L;

	@Id
    private String idExtract;
    
    @ManyToOne
    private Establishment stablishment;
    
    private String productDescription;
    
    private LocalDate dateBuy;
    
    @ManyToOne
    private Card card;
    
    private BigDecimal value;
}

