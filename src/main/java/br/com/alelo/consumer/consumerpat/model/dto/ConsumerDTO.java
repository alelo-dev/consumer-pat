package br.com.alelo.consumer.consumerpat.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.alelo.consumer.consumerpat.model.entity.Consumer;

public class ConsumerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Integer documentNumber;
	private Date birthDate;

	ContactsDTO contacts;

	List<AddressDTO> address = new ArrayList<>();

	List<CardsDTO> cards = new ArrayList<>();

	public ConsumerDTO(Consumer entity) {

		this.id = entity.getId();
		this.name = entity.getName();
		this.documentNumber = entity.getDocumentNumber();
		this.birthDate = entity.getBirthDate();
		this.contacts = new ContactsDTO(entity.getContacts());
		this.address = entity.getAddress().stream()
											.map(obj -> new AddressDTO(obj))
											.collect(Collectors.toList());
		this.cards = entity.getCards().stream()
									  .map(obj -> new CardsDTO(obj))
									  .collect(Collectors.toList());
	}

	public ConsumerDTO() {

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

	public ContactsDTO getContacts() {
		return contacts;
	}

	public void setContacts(ContactsDTO contacts) {
		this.contacts = contacts;
	}

	public List<AddressDTO> getAddress() {
		return address;
	}

	public void setAddress(List<AddressDTO> address) {
		this.address = address;
	}

	public List<CardsDTO> getCards() {
		return cards;
	}

	public void setCards(List<CardsDTO> cards) {
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
		ConsumerDTO other = (ConsumerDTO) obj;
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
