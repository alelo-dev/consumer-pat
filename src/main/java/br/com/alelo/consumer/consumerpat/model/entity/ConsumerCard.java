package br.com.alelo.consumer.consumerpat.model.entity;

import javax.persistence.*;

import br.com.alelo.consumer.consumerpat.model.enumeration.CardType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Consumer consumer;

}