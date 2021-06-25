package br.com.alelo.consumer.consumerpat.model.dto;

import java.io.Serializable;
import java.math.BigInteger;

import br.com.alelo.consumer.consumerpat.model.entity.Cards;


public class CardsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private BigInteger cardNumber;
	
	private Double cardBalance;
	
	private Integer cardsType;
	
	private String cardsDescription;

	
	public CardsDTO() {		
	}	
		
	public CardsDTO(Cards entity) {
		
		this.id = entity.getId();
		this.cardNumber = entity.getCardNumber();
		this.cardBalance = entity.getCardBalance();
		this.cardsType =entity.getCardsType().getCod();
		this.cardsDescription=entity.getCardsType().getDescricao();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigInteger getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(BigInteger cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(Double cardBalance) {
		this.cardBalance = cardBalance;
	}


	public Integer getCardsType() {
		return cardsType;
	}

	public void setCardsType(Integer cardsType) {
		this.cardsType = cardsType;
	}

	public String getCardsDescription() {
		return cardsDescription;
	}

	public void setCardsDescription(String cardsDescription) {
		this.cardsDescription = cardsDescription;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardBalance == null) ? 0 : cardBalance.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cardsType == null) ? 0 : cardsType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardsDTO other = (CardsDTO) obj;
		if (cardBalance == null) {
			if (other.cardBalance != null)
				return false;
		} else if (!cardBalance.equals(other.cardBalance))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cardsType == null) {
			if (other.cardsType != null)
				return false;
		} else if (!cardsType.equals(other.cardsType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

	
}
