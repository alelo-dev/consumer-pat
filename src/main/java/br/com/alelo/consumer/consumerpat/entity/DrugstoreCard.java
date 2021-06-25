package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class DrugstoreCard extends Card {

	public DrugstoreCard(int number, double balance) {
		this.number = number;
		this.balance = balance;
	}
}
