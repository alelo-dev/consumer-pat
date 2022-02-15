package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alelo.consumer.consumerpat.enumerator.EstablishmentType;
import lombok.Data;

@Entity
@Data
public class Card {

    @Id
	private String number;
    
    private double balance;
    

    @Enumerated(EnumType.STRING)
	private EstablishmentType tipo;
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_consumer")
	private Consumer consumer;	
}
