package br.com.alelo.consumer.consumerpat.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.alelo.consumer.consumerpat.model.enums.TypeCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Card implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer cardNumber;

	private Double balance;

	@Enumerated(EnumType.STRING)
	private TypeCard typeCard;
	
	public Card(Integer cardNumber, TypeCard typeCard) {
		this.cardNumber = cardNumber;
		this.typeCard = typeCard;
	}
}
