package br.com.alelo.consumer.consumerpat.model.dto;

import java.io.Serializable;
import java.math.BigInteger;


public class CardsBalanceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

		
	private BigInteger cardNumber;
	
	private Double cardBalance;
	
	private Integer cardsType;	
	

	
	public CardsBalanceDTO() {		
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




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardBalance == null) ? 0 : cardBalance.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
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
		CardsBalanceDTO other = (CardsBalanceDTO) obj;
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
		return true;
	}


	

	

	
}
