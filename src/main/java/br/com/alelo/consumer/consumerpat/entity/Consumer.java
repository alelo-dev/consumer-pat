package br.com.alelo.consumer.consumerpat.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "consumer")
public class Consumer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 80, nullable = false)
	private String name;

	@Column(name = "documentNumber")
	private int documentNumber;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime birthDate;
	
	@Embedded
	private Address address;
	
	@Embedded
	private Contacts contacts;

	// cards
	
	private int foodCardNumber;
	private double foodCardBalance; 

	private int fuelCardNumber;
	private double fuelCardBalance;

	private int drugstoreNumber;
	private double drugstoreCardBalance;

}
