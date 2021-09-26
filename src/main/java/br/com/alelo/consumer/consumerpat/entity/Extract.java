package br.com.alelo.consumer.consumerpat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;


@Data
@Entity
@Builder
@Table(name = "TB_EXTRACT")
public class Extract extends BaseEntity{


	@JoinColumn(name = "ESTABLISHMENT_IDe", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	Establishment establishment;
	
	@JoinColumn(name = "CARD_CONSUMER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    CardConsumer cardConsumer;
	
	
	@Column(name = "PURCHASE_VALUE")
	private Double purchaseValue;

}
