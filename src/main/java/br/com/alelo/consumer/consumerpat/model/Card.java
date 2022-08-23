package br.com.alelo.consumer.consumerpat.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.emun.TypeCard;
import br.com.alelo.consumer.consumerpat.model.converter.TypeCardConverter;
import lombok.Data;

@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
	@Convert(converter = TypeCardConverter.class)
	private TypeCard type;
	
	@Column(unique = true)
	private Long number;
	
	private double balance;
	
	public double debit(double value) {
		double calculateBuy = type.calculateBuy(value);
		setBalance( balance - calculateBuy);
		return calculateBuy;
	}
}