package br.com.alelo.consumer.consumerpat.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Address {
	
	@Id
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
	
    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;
    
}
