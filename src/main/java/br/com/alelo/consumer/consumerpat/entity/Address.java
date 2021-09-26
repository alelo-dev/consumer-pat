package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_ADDRESS")
public class Address extends BaseEntity{

	@Column(name = "ADDRESS_STREET")
	private String addressStreet;

	@Column(name = "ADDRESS_NUMBER")
	private Integer addressNumber;

	@Column(name = "CITY")
	private String city;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "POSTAL_CODE")
	private String postalCode;
}
