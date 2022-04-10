package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Extract implements Serializable {

	private static final long serialVersionUID = 3465500229975213476L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID idExtract;
    
    @ManyToOne
    private Establishment stablishment;
    
    private String productDescription;
   
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    LocalDateTime dateBuy;
    
    @ManyToOne
    private Card card;
    
    private BigDecimal value;
}

