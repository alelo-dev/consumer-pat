package br.com.alelo.consumer.consumerpat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.alelo.consumer.consumerpat.exception.UnsupportedOperationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
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

/**
 * Classe de domínio representando um cartão. 
 * Nomes das tabelas no BD em 'plural'.
 */
@Entity
@Table(name = "cards")
public class Card implements Comparable<Card> {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
		
	private final String number;

	@Enumerated(EnumType.STRING)
	private final BusinessType type;

	private BigDecimal balance;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "consumer_id")
	@JsonIgnore
	private Consumer consumer;

	private final LocalDate expirationDate;

	public Card() {
		this.number = null;
		this.type = null;
		this.expirationDate = null;
		this.consumer = null;
	}
	
	public Card(String number, BusinessType type, BigDecimal balance, Consumer consumer,
			LocalDate expirationDate) {
		this.number = number;
		this.type = type;
		this.balance = balance.setScale(2, RoundingMode.HALF_UP);
		this.consumer = consumer;
		this.expirationDate = expirationDate;
	}

	public long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public BusinessType getType() {
		return type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	public void debit(final BigDecimal value) {
		if ( value.compareTo(balance) > 0)
			throw new UnsupportedOperationException("Saldo indisponível");

		balance = balance.subtract(value);
		balance.setScale(2, RoundingMode.HALF_UP);
	}
	
	public void credit(final BigDecimal value) {
		balance = balance.add(value);
		balance.setScale(2, RoundingMode.HALF_UP);
	}

	@Override // Usando apenas o numero e o tipo do cartao para diferencia-lo
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Card card = (Card) obj;
		
		return Objects.equals(number, card.number)
				&& Objects.equals(expirationDate, card.expirationDate)
				&& type == card.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, type, expirationDate);
	}

	@Override
	public int compareTo(Card card) {
		return this.number.compareTo(card.number);
	}
}
