package br.com.alelo.consumer.consumerpat.model.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Consumer implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer documentNumber;
    private Date birthDate;
    
    @OneToOne(mappedBy="consumer", cascade=CascadeType.ALL)
    Contacts contacts;
    
    @OneToMany(mappedBy="consumer", cascade=CascadeType.ALL)
    List<Address> address= new ArrayList<>();
    
    @OneToMany(mappedBy="consumer", cascade=CascadeType.ALL)
    List<Cards> cards= new ArrayList<>();
    
    
    
	public Consumer(Integer id, String name, Integer documentNumber, Date birthDate, Contacts contacts,
			List<Address> address, List<Cards> cards) {
		
		this.id = id;
		this.name = name;
		this.documentNumber = documentNumber;
		this.birthDate = birthDate;
		this.contacts = contacts;
		this.address = address;
		this.cards = cards;
	}


	public Consumer() {
		
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getDocumentNumber() {
		return documentNumber;
	}


	public void setDocumentNumber(Integer documentNumber) {
		this.documentNumber = documentNumber;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Contacts getContacts() {
		return contacts;
	}


	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}


	public List<Address> getAddress() {
		return address;
	}


	public void setAddress(List<Address> address) {
		this.address = address;
	}


	public List<Cards> getCards() {
		return cards;
	}


	public void setCards(List<Cards> cards) {
		this.cards = cards;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result + ((contacts == null) ? 0 : contacts.hashCode());
		result = prime * result + ((documentNumber == null) ? 0 : documentNumber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Consumer other = (Consumer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		if (contacts == null) {
			if (other.contacts != null)
				return false;
		} else if (!contacts.equals(other.contacts))
			return false;
		if (documentNumber == null) {
			if (other.documentNumber != null)
				return false;
		} else if (!documentNumber.equals(other.documentNumber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
		
	
	
		
	



	

    




}
