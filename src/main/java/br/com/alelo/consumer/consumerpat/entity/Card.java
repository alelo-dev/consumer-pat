package br.com.alelo.consumer.consumerpat.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.alelo.consumer.consumerpat.entity.enums.CardType;
import lombok.Data;

@Data
@Entity
public class Card {
	
	@Id
	private String number;
	private CardType type;
	private BigDecimal balance = BigDecimal.ZERO;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
	
	public BigDecimal add(BigDecimal value) {
		if(balance == null)
			balance = BigDecimal.ZERO;
		balance = balance.add(value);
		return balance;
	}
	
	public BigDecimal remove(BigDecimal value) {
		if(balance == null)
			balance = BigDecimal.ZERO;
		balance = balance.subtract(value);
		return balance;
	}
	
}
