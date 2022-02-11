package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "street", nullable = false, length = 255)
	private String street;
	
	@Column(name = "number", nullable = false)
    private int number;
	
	@Column(name = "city", nullable = false, length = 255)
    private String city;
	
	@Column(name = "country", nullable = false, length = 255)
    private String country;
	
	@Column(name = "postalCode", nullable = false)
    private String postalCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="consumer_id", nullable=false)
    private Consumer consumer;
}
