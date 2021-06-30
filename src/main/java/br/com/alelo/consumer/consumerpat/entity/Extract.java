package br.com.alelo.consumer.consumerpat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Extract {

	@Id
	Integer id;
	Integer cardNumber;
	Date dateBuy;
	String establishmentName;
	Integer establishmentNameId;
	String productDescription;
	Double value;

	// Construtor default
	public Extract() {
	}

	public Extract(Integer id, Integer establishmentNameId, String establishmentName, String productDescription, Date dateBuy,
			Integer cardNumber, Double value) {
		this.id = id;
		this.cardNumber = cardNumber;
		this.dateBuy = dateBuy;
		this.establishmentName = establishmentName;
		this.establishmentNameId = establishmentNameId;
		this.productDescription = productDescription;
		this.value = value;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the cardNumber
	 */
	public Integer getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the dateBuy
	 */
	public Date getDateBuy() {
		return dateBuy;
	}

	/**
	 * @param dateBuy the dateBuy to set
	 */
	public void setDateBuy(Date dateBuy) {
		this.dateBuy = dateBuy;
	}

	/**
	 * @return the establishmentName
	 */
	public String getEstablishmentName() {
		return establishmentName;
	}

	/**
	 * @param establishmentName the establishmentName to set
	 */
	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	/**
	 * @return the establishmentNameId
	 */
	public Integer getEstablishmentNameId() {
		return establishmentNameId;
	}

	/**
	 * @param establishmentNameId the establishmentNameId to set
	 */
	public void setEstablishmentNameId(Integer establishmentNameId) {
		this.establishmentNameId = establishmentNameId;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Extract [id=" + id + ", establishmentNameId=" + establishmentNameId + ", establishmentName="
				+ establishmentName + ", productDescription=" + productDescription + ", dateBuy=" + dateBuy
				+ ", cardNumber=" + cardNumber + ", value=" + value + "]";
	}

}
