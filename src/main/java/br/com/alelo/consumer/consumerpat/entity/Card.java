package br.com.alelo.consumer.consumerpat.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "card")
public class Card  implements Serializable {

	
	private static final long serialVersionUID = 2534791474554349883L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "type", nullable = false)
	private String type;	
	
	@Column(name = "number", nullable = false)	
	private Integer number;

	@Column(name = "balance", nullable = false)
    private Double balance;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Consumer consumer;
  
    
	public Card(){
	}

	//-----------Getters and Setters-------------//
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}


	public Consumer getConsumer() {
		return consumer;
	}


	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}   
  
}