package br.com.alelo.consumer.consumerpat.entity;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name = "TB_CONSUMER")
public class Consumer extends BaseEntity{



	@Column(name = "NAME")
	private String name;

	@Column(name = "DOCUMENT_NUMBER")
	private Long documentNumber;

	@Column(name = "BIRTH_DATE")
	private LocalDate birthDate;
	
	@Column(name = "EMAIL")
	private String email;

	//contacts
	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;

	//Address
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "CONSUMER_ID")
	private List<CardConsumer> cards;

}
