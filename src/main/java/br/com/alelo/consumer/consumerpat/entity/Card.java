package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
@MappedSuperclass
public class Card {
	protected int number;
	protected double balance;
}
