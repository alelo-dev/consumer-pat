package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;

@Entity
class FoodCard extends Card {
	public FoodCard() {
		// TODO Auto-generated constructor stub
	}

	public FoodCard(int number, double balance) {
		super(number, balance);
	}
}
