package br.com.alelo.consumer.consumerpat.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe de domínio representando um consumidor. 
 * Nomes das tabelas no BD em 'plural'.
 * 
 */
@Entity
@Table(name = "consumers")
public final class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private final String cpf;
	private final String name;
	private LocalDate birthDate;
	private String email;
	private String phone;
	private String mobilePhone;
	private String residencePhone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	@OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Card> cards;

	public Consumer() {
		this.cpf = null;
		this.name = null;
	}
	
	public Consumer(final String cpf, final String name) {
		this.cpf = cpf;
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getResidencePhone() {
		return residencePhone;
	}

	public void setResidencePhone(String residencePhone) {
		this.residencePhone = residencePhone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Consumer consumer = (Consumer) obj;
		Collections.sort(cards); // gar. deterministica
		return Objects.equals(address, consumer.address) 
				&& Objects.equals(birthDate, consumer.birthDate)
				&& Objects.equals(cards, consumer.cards) 
				&& Objects.equals(cpf, consumer.cpf)
				&& Objects.equals(email, consumer.email) 
				&& Objects.equals(mobilePhone, consumer.mobilePhone) 
				&& Objects.equals(name, consumer.name)
				&& Objects.equals(phone, consumer.phone) 
				&& Objects.equals(residencePhone, consumer.residencePhone);
	}

	@Override
	public int hashCode() {
		Collections.sort(cards); // gar. deterministica
		return Objects.hash(address, birthDate, cards, cpf, email, mobilePhone, name, phone, residencePhone);
	}
	

}