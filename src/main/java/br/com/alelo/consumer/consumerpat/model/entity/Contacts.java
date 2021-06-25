package br.com.alelo.consumer.consumerpat.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contacts implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="consumer_id")
	private Consumer consumer;
	private Integer mobilePhoneNumber;
	private Integer residencePhoneNumber;
	private Integer phoneNumber;
	private String email;
	
	
	public Contacts() {
		
	}
	public Contacts(Integer id, Integer mobilePhoneNumber, Integer residencePhoneNumber, Integer phoneNumber,
			String email) {
		
		this.id = id;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.residencePhoneNumber = residencePhoneNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		
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
		Contacts other = (Contacts) obj;
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
