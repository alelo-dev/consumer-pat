package br.com.alelo.consumer.consumerpat.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.alelo.consumer.consumerpat.domain.enums.CardType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(exclude = "consumer")
@ToString(exclude = "consumer")
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
