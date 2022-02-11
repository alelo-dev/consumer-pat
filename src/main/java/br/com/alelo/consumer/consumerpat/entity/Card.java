package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	
	@Column(name = "number", nullable = false, length = 50)
	private String number;
	
	@Column(name = "balance", nullable = false)
	private double balance;
	
	@Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
	private EstablishmentType type;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="consumer_id", nullable=false)
    private Consumer consumer;

}
