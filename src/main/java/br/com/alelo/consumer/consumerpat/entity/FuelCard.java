package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;

@Entity
class FuelCard extends Card {
	public FuelCard() {
		// TODO Auto-generated constructor stub
	}

	public FuelCard(int number, double balance) {
		super(number, balance);
	}
}
