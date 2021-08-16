package br.com.alelo.consumer.consumerpat.entity.card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "CARD")
public class Card {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
    private String number;	
	
    private Double balance;
       
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)   
    private Category category;   
}
