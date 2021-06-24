package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConsumerAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;
	
	private String street;
	private Integer number;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private Integer portalCode;
	
	public void merge(final ConsumerAddress consumerAdressConverted) {
		this.street = consumerAdressConverted.getStreet();
		this.number = consumerAdressConverted.getNumber(); 
		this.city = consumerAdressConverted.getCity();
		this.country = consumerAdressConverted.getCountry();
		this.portalCode = consumerAdressConverted.getPortalCode();
	}

}