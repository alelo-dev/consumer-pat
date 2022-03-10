package br.com.alelo.consumer.consumerpat.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Builder
public class Card implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer foodCardNumber;
	private Integer fuelCardNumber;
	private Integer drugstoreNumber;
	private Double fuelCardBalance;
	private Double foodCardBalance;
	private Double drugstoreCardBalance;
}
