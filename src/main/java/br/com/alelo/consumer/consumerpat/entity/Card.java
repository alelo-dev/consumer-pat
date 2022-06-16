package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.entity.enums.Type;
import lombok.Data;

@Data
@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int number;
	private double balance;

	public Card() {
		// TODO Auto-generated constructor stub
	}

	Card(int number, double balance) {
		this.number = number;
		this.balance = balance;
	}

	public Type getType() {
		String typeStr = this.getClass().getName();
		if (typeStr.toUpperCase().contains(Type.FOOD.name()))
			return Type.FOOD;
		else if (typeStr.toUpperCase().contains(Type.FUEL.name()))
			return Type.FUEL;
		return Type.DRUGSTORE;
	}
}
