package br.com.alelo.consumer.consumerpat.model.entity;

import java.time.LocalDate;

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

import br.com.alelo.consumer.consumerpat.model.enumeration.CardType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class Extract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private CardType establishmentNameId;
	
	@Column(nullable = false)
	private String establishmentName;
	
	@Column(nullable = false)
	private String productDescription;
	
	@Column(nullable = false)
	private LocalDate dateBuy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumer_card_id", nullable = false)
	private ConsumerCard consumerCard;
	
	@Builder.Default
	private Double value = 0.0;

}