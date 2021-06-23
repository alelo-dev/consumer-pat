package br.com.alelo.consumer.consumerpat.model.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer documentNumber;
	
	private LocalDate birthDate;

	//contacts
	private Integer mobilePhoneNumber;
	
	private Integer residencePhoneNumber;
	
	private Integer phoneNumber;
	
	@Column(nullable = false)
	private String email;

	//Address
	private String street;
	private Integer number;
	private String city;
	private String country;
	private Integer portalCode;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<ConsumerCard> consumerCards;

	public void merge(final Consumer consumerConverted) {
		this.name = consumerConverted.getEmail();
		this.documentNumber = consumerConverted.getDocumentNumber(); 
		this.birthDate = consumerConverted.getBirthDate();
		this.mobilePhoneNumber = consumerConverted.getMobilePhoneNumber();
		this.residencePhoneNumber = consumerConverted.getResidencePhoneNumber();
		this.phoneNumber = consumerConverted.getPhoneNumber();
		this.email = consumerConverted.getEmail();
		this.street = consumerConverted.getStreet();
		this.number = consumerConverted.getNumber();
		this.city = consumerConverted.getCity();
		this.country = consumerConverted.getCountry();
		this.portalCode = consumerConverted.getPortalCode();
	}

}