package br.com.alelo.consumer.consumerpat.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(unique = true)
	private String documentNumber;

	private LocalDate birthDate;

	@OneToOne(mappedBy = "consumer", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONSUMER_ID", referencedColumnName = "ID")
	private List<Contact> contacts;

	@OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Card> cards;

	public Consumer(String name, String documentNumber, LocalDate birthDate) {
	    this.name = name;
	    this.documentNumber = documentNumber;
	    this.birthDate = birthDate;
	}

}
