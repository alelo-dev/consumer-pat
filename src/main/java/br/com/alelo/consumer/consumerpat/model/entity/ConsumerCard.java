package br.com.alelo.consumer.consumerpat.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
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
import javax.persistence.UniqueConstraint;

import br.com.alelo.consumer.consumerpat.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cardNumber"})})
public class ConsumerCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String cardNumber;

	@Column(nullable=false, precision=10, scale=2)
	@Builder.Default
	private BigDecimal cardBalance = BigDecimal.ZERO;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CardType cardType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "consumer_id", nullable = false)
	private Consumer consumer;

}