package br.com.alelo.consumer.consumerpat.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.alelo.consumer.consumerpat.model.enums.CardsType;

@Entity
public class Cards implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name="consumer_id")
	private Consumer consumer;
	
	private BigInteger cardNumber;
	
	protected Double cardBalance;
	
	private Integer cardsType;

	
	public Cards() {
		
	}	
	

	public Cards(Integer id, Consumer consumer, BigInteger cardNumber, Double cardBalance, CardsType cardsType) {
		
		this.id = id;
		this.consumer = consumer;		
		this.cardNumber = cardNumber;
		this.cardBalance = cardBalance;
		this.cardsType = (cardsType==null) ? null : cardsType.getCod();
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public Consumer getConsumer() {
		return consumer;
	}


	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
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

	public CardsType getCardsType() {
		return CardsType.toEnum(cardsType);
	}

	public void setCardsType(CardsType cardsType) {
		this.cardsType = cardsType.getCod();
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
		Cards other = (Cards) obj;
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
