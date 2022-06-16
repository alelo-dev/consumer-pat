package br.com.alelo.consumer.consumerpat.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Extract {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@ManyToOne
	Establishment establishment;
	@ManyToOne
	Card card;
	Date dateBuy;
	double value;
	String productDescription;

	public Extract(String productDescription, Date date, Card card, Establishment establishment, double value) {
		this.productDescription = productDescription;
		this.dateBuy = date;
		this.card = card;
		this.establishment = establishment;
		this.value = value;
	}
}
