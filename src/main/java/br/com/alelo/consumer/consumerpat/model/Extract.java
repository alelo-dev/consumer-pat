package br.com.alelo.consumer.consumerpat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Extract implements Serializable{

	private static final long serialVersionUID = -3254627112617142380L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer establishmentNameId;
	private String establishmentName;
	private String productDescription;

	@Temporal(TemporalType.DATE)
	private Date dateBuy;
	private Integer cardNumber;
	private Double value;

	public Extract(String productDescription, Date dateBuy, Integer cardNumber, Double value) {
		this.productDescription = productDescription;
		this.dateBuy = dateBuy;
		this.cardNumber = cardNumber;
		this.value = value;
	}

	public Extract(String establishmentName, String productDescription, Date dateBuy, Integer cardNumber, Double value,
			Integer establishmentNameId) {
		this.establishmentNameId = establishmentNameId;
		this.establishmentName = establishmentName;
		this.productDescription = productDescription;
		this.dateBuy = dateBuy;
		this.cardNumber = cardNumber;
		this.value = value;
	}
}
