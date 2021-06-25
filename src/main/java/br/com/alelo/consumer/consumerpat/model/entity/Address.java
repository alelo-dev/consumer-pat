package br.com.alelo.consumer.consumerpat.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.alelo.consumer.consumerpat.model.dto.AddressDTO;
import br.com.alelo.consumer.consumerpat.model.enums.AddressType;

@Entity
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="consumer_id")
	private Consumer consumer;	
	
	private Integer addressType;
	private String street;
	private Integer number;
	private String city;
	private String country;
	private Integer portalCode;
	
    
    public Address() {    	
    }        
 
    
	public Address(Integer id,  AddressType addressType, String street, Integer number, String city,
			String country, Integer portalCode) {		
		this.id = id;
		this.addressType = (addressType==null) ? null : addressType.getCod();
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
		this.portalCode = portalCode;
	}

	public static Address from(AddressDTO dto) {
		return new Address(dto.getId(),  AddressType.toEnum(dto.getAddressType()),
				dto.getStreet(), dto.getNumber(), dto.getCity(),  dto.getCountry(), dto.getPortalCode());

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public Consumer getConsumer() {
		return consumer;
	}


	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}


	public AddressType getAddressType() {
		return AddressType.toEnum(addressType) ;
	}


	public void setAddressType(AddressType type) {
		this.addressType = type.getCod();
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPortalCode() {
		return portalCode;
	}

	public void setPortalCode(Integer portalCode) {
		this.portalCode = portalCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((portalCode == null) ? 0 : portalCode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (portalCode == null) {
			if (other.portalCode != null)
				return false;
		} else if (!portalCode.equals(other.portalCode))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
    
    
}
