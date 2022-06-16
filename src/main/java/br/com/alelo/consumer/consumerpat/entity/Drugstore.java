package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;

@Entity
class DrugstoreCard extends Card {

	public DrugstoreCard() {
		// TODO Auto-generated constructor stub
	}

	public DrugstoreCard(int number, double balance) {
		super(number, balance);
	}
}
