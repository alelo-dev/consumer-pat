package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

//TODO NAO FOI DEFINIDO OS CAMPOS OBRIGATORIOS.
@Data
@Entity
public class Address {

	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
	private String street;
	private int number;
	private String city;
	private String country;
	private int portalCode;
	@OneToOne
  @JoinColumn(name = "consumer_id")
	private Consumer consumer;
}
