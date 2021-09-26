package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CARD_CONSUMER")
public class CardConsumer extends BaseEntity{

	@OneToOne
	@JoinColumn(name="consumer_id", referencedColumnName="id")
	private Consumer consumer;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private CardType cardType;

	@Column(name = "CARD_NUMBER", nullable = false, unique = true)
	private String cardNumber;

	@Column(name = "BALANCE")
	private Double balance;

}
