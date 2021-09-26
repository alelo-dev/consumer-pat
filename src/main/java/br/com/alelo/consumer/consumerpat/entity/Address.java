package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "address")
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
    private int number;
    
	@Column(name = "city")
    private String city;
    
	@Column(name = "country")
    private String country;
    
	@Column(name = "portalCode")
    private  int portalCode;
    
}
