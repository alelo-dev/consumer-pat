package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Card {

	private Type type;
	
	@Column(unique = true)
	private int number;
	
	private double balance;
	
	
	
	
	public enum Type { DRUG, FOOD, FUEL}
		
}