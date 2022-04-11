package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Adrress {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
	
	String street;
    int number;
    String city;
    String country;
    int portalCode;
}
