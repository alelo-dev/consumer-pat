package br.com.alelo.consumer.consumerpat.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alelo.consumer.consumerpat.model.entity.Address;
import br.com.alelo.consumer.consumerpat.model.entity.Consumer;
import br.com.alelo.consumer.consumerpat.model.enums.AddressType;


public class AddressDTO implements Serializable{
	private static final long serialVersionUID = 1L;


	private Integer id;


	private Integer addressType;
	private String addressDescription;
	private String street;
	private Integer number;
	private String city;
	private String country;
	private Integer portalCode;
	
    
    public AddressDTO() {    	
    }        
 
    
	public AddressDTO(Address entity) {
		
		this.id = entity.getId();
		
		this.addressType = entity.getAddressType().getCod();
		this.addressDescription = entity.getAddressType().getDescricao();
		this.street = entity.getStreet();
		this.number = entity.getNumber();
		this.city = entity.getCity();
		this.country = entity.getCountry();
		this.portalCode = entity.getPortalCode();
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public Integer getAddressType() {
		return addressType;
	}


	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}


	public String getAddressDescription() {
		return addressDescription;
	}


	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
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
		AddressDTO other = (AddressDTO) obj;
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
