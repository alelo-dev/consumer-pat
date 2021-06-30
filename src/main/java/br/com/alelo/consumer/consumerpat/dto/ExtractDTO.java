package br.com.alelo.consumer.consumerpat.dto;

public class ExtractDTO {

	Integer id;
	Integer establishmentNameId;
	Integer establishmentType;
	String establishmentName;
	String productDescription;
	Integer cardNumber;
	Double value;
	String mensagem;

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
	 * @return the establishmentType
	 */
	public Integer getEstablishmentType() {
		return establishmentType;
	}

	/**
	 * @param establishmentType the establishmentType to set
	 */
	public void setEstablishmentType(Integer establishmentType) {
		this.establishmentType = establishmentType;
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

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
