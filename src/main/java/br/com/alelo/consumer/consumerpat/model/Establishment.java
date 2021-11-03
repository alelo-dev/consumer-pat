package br.com.alelo.consumer.consumerpat.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe de domínio representando um estabelecimento comercial.
 * Nomes das tabelas no BD em 'plural'.
 *
 */
@Entity
@Table(name="establishments")
public final class Establishment {
		
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	// para buscas unicas, nao implementado
	// private final String cnpj;   
	
	private final String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private final BusinessType type;

	public Establishment() {
		this.name = null;
		this.type = null;
	}
	
	public Establishment(String name, Address address, BusinessType type) {
		this.name = name;
		this.address = address;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public BusinessType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Establishment establishment = (Establishment) obj;
		
		return Objects.equals(name, establishment.name) 
				&& type == establishment.type;
	}
}