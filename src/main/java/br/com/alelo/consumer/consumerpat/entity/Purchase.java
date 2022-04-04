package br.com.alelo.consumer.consumerpat.entity;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Purchase")
public class Purchase implements Serializable  {

	private static final long serialVersionUID = 2605048758680711054L;


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	@Column(name = "dateBuy", nullable = false)
	@Temporal(TemporalType.DATE) 
	private Date dateBuy;	
    
	@Column(name = "value", nullable = false)
	Double value;

	@JoinColumn(nullable = false)
	@ManyToOne
    private Card card;

	@JoinColumn(nullable = false)	
	@ManyToOne
	private Establishment establishment;

	//TODO - Ask recruter about product registration. After that, decide if I must to create a table to save all products.
    //product
	@Column(name = "productDescription", nullable = false)
    String productDescription;
    
    
	public Purchase() {
	}

	//-----------Getters and Setters-------------//
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getDateBuy() {
		return dateBuy;
	}

	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public Card getCard() {
		return card;
	}


	public void setCard(Card card) {
		this.card = card;
	}


	public Establishment getEstablishment() {
		return establishment;
	}


	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
}