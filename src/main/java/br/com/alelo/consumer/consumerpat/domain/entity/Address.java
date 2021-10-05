package br.com.alelo.consumer.consumerpat.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Address {

	
	@Column(name = "address_public_area", nullable = false)
    protected String publicArea;
	
	@Column(name = "address_number", nullable = false)
	protected String number;
	
	@Column(name = "address_zip_code", nullable = false)
	protected String zipCode;
    
	@JoinColumn(name = "address_city_id", nullable = false)
	@ManyToOne
	protected City city;


}
