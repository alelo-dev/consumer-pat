package br.com.alelo.consumer.consumerpat.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.model.entity.Contacts;


public class ContactsDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer mobilePhoneNumber;
	private Integer residencePhoneNumber;
	private Integer phoneNumber;
	private String email;
	
	
	public ContactsDTO() {
		
	}
	public ContactsDTO(Contacts entity) {
		
		this.id = entity.getId();
		this.mobilePhoneNumber = entity.getMobilePhoneNumber();
		this.residencePhoneNumber = entity.getResidencePhoneNumber();
		this.phoneNumber = entity.getMobilePhoneNumber();
		this.email = entity.getEmail();
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	public void setMobilePhoneNumber(Integer mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}
	public Integer getResidencePhoneNumber() {
		return residencePhoneNumber;
	}
	public void setResidencePhoneNumber(Integer residencePhoneNumber) {
		this.residencePhoneNumber = residencePhoneNumber;
	}
	public Integer getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobilePhoneNumber == null) ? 0 : mobilePhoneNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((residencePhoneNumber == null) ? 0 : residencePhoneNumber.hashCode());
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
		ContactsDTO other = (ContactsDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobilePhoneNumber == null) {
			if (other.mobilePhoneNumber != null)
				return false;
		} else if (!mobilePhoneNumber.equals(other.mobilePhoneNumber))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (residencePhoneNumber == null) {
			if (other.residencePhoneNumber != null)
				return false;
		} else if (!residencePhoneNumber.equals(other.residencePhoneNumber))
			return false;
		return true;
	}
	
	
	
}
