package br.com.alelo.consumer.consumerpat.entity;

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

import br.com.alelo.consumer.consumerpat.enumeration.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

	@Column(nullable = false)
	@Builder.Default
	private Double cardBalance = 0.0;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CardType cardType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "consumer_id", nullable = false)
	private Consumer consumer;

}