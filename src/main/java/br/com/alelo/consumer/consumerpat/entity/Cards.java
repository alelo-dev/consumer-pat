package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "cards")
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "foodCardNumber")
	private int foodCardNumber;

	@Column(name = "foodCardBalance")
	private double foodCardBalance;

	@Column(name = "fuelCardNumber")
	private int fuelCardNumber;

	@Column(name = "fuelCardBalance")
	private double fuelCardBalance;

	@Column(name = "drugstoreNumber")
	private int drugstoreNumber;

	@Column(name = "drugstoreCardBalance")
	private double drugstoreCardBalance;
}
