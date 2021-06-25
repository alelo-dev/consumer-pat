package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class FuelCard extends Card {

	public FuelCard(int number, double balance) {
		this.number = number;
		this.balance = balance;
	}
}
