package br.com.alelo.consumer.consumerpat.entity.consumer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	private String street;
	
	private Integer number;
	
	private String city;
	
	private String country;
	
	private Integer portalCode;
}
